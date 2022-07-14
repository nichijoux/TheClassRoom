package com.zh.tcr.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.common.client.CouponInfoFeignClient;
import com.zh.tcr.common.client.CourseFeignClient;
import com.zh.tcr.common.client.UserInfoFeignClient;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.common.utils.AuthContextHolder;
import com.zh.tcr.common.utils.OrderNoUtils;
import com.zh.tcr.model.entity.activity.CouponInfo;
import com.zh.tcr.model.entity.order.OrderDetail;
import com.zh.tcr.model.entity.order.OrderInfo;
import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.model.entity.vod.Course;
import com.zh.tcr.model.vo.order.OrderFormVO;
import com.zh.tcr.model.vo.order.OrderInfoQueryCondition;
import com.zh.tcr.model.vo.order.OrderInfoVO;
import com.zh.tcr.order.mapper.OrderInfoMapper;
import com.zh.tcr.order.service.OrderDetailService;
import com.zh.tcr.order.service.OrderInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * <p>
 * 订单表 订单表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
    private OrderDetailService orderDetailService;

    private CourseFeignClient courseFeignClient;

    private UserInfoFeignClient userInfoFeignClient;

    private CouponInfoFeignClient couponInfoFeignClient;

    @Autowired
    public void setOrderDetailService(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @Autowired
    public void setCourseFeignClient(CourseFeignClient courseFeignClient) {
        this.courseFeignClient = courseFeignClient;
    }

    @Autowired
    public void setUserInfoFeignClient(UserInfoFeignClient userInfoFeignClient) {
        this.userInfoFeignClient = userInfoFeignClient;
    }

    @Autowired
    public void setCouponInfoFeignClient(CouponInfoFeignClient couponInfoFeignClient) {
        this.couponInfoFeignClient = couponInfoFeignClient;
    }

    // 分页查询订单表信息
    @Override
    public void pageQueryOrderInfo(Page<OrderInfo> orderInfoPage, OrderInfoQueryCondition queryCondition) {
        if (queryCondition == null) {
            baseMapper.selectPage(orderInfoPage, null);
            return;
        }
        QueryWrapper<OrderInfo> wrapper = new QueryWrapper<>();
        // 获取查询条件
        Integer orderStatus = queryCondition.getOrderStatus();
        Long userId = queryCondition.getUserId();
        String outTradeNo = queryCondition.getOutTradeNo();
        String phone = queryCondition.getPhone();
        String createTimeBegin = queryCondition.getCreateTimeBegin();
        String createTimeEnd = queryCondition.getCreateTimeEnd();
        // 设定查询条件
        if (!StringUtils.isEmpty(orderStatus)) wrapper.eq("order_status", orderStatus);
        if (!StringUtils.isEmpty(userId)) wrapper.eq("user_id", userId);
        if (!StringUtils.isEmpty(outTradeNo)) wrapper.eq("out_trade_no", outTradeNo);
        if (!StringUtils.isEmpty(phone)) wrapper.eq("phone", phone);
        if (!StringUtils.isEmpty(createTimeBegin)) wrapper.ge("create_time", createTimeBegin);
        if (!StringUtils.isEmpty(createTimeEnd)) wrapper.le("create_time", createTimeEnd);
        // 查询
        baseMapper.selectPage(orderInfoPage, wrapper);
        // 订单里面包含详情内容,封装详情数据,根据订单id查询详情
        orderInfoPage.getRecords().forEach(this::getOrderDetail);
    }

    // 生成订单
    @Override
    public Long generateOrder(OrderFormVO orderFormVO) {
        // 获取生产订单的条件值
        Long courseId = orderFormVO.getCourseId();
        Long couponId = orderFormVO.getCouponId();
        Long userId = AuthContextHolder.getUserId();
        // 判断用户是否已经生成了订单,生成了则直接返回
        QueryWrapper<OrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("user_id", userId);
        OrderDetail orderDetails = orderDetailService.getOne(wrapper);
        if (orderDetails != null) {
            return orderDetails.getOrderId();
        }
        // 根据课程id获取课程信息
        Course course = courseFeignClient.remoteGetCourse(courseId);
        if (course == null) {
            throw new TCRException(20001, "课程不存在");
        }
        // 根据用户id获取用户信息
        UserInfo userInfo = userInfoFeignClient.remoteGetUserInfo(userId);
        if (userInfo == null) {
            throw new TCRException(20001, "用户不存在");
        }
        // 根据优惠卷id获取优惠卷信息
        BigDecimal couponReduce = new BigDecimal(0);
        if (couponId != null) {
            CouponInfo couponInfo = couponInfoFeignClient.remoteGetCouponInfo(couponId);
            couponReduce = couponInfo.getAmount();
        }
        // 封装订单需要的对象，完成数据库添加
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setNickName(userInfo.getNickName());
        orderInfo.setPhone(userInfo.getPhone());
        orderInfo.setProvince(userInfo.getProvince());
        orderInfo.setOriginAmount(course.getPrice());
        orderInfo.setCouponReduce(couponReduce);
        orderInfo.setFinalAmount(orderInfo.getOriginAmount().subtract(orderInfo.getCouponReduce()));
        orderInfo.setOutTradeNo(OrderNoUtils.getOrderNo());
        orderInfo.setTradeBody(course.getTitle());
        orderInfo.setOrderStatus("0");
        baseMapper.insert(orderInfo);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderInfo.getId());
        orderDetail.setUserId(userId);
        orderDetail.setCourseId(courseId);
        orderDetail.setCourseName(course.getTitle());
        orderDetail.setCover(course.getCover());
        orderDetail.setOriginAmount(course.getPrice());
        orderDetail.setCouponReduce(new BigDecimal(0));
        orderDetail.setFinalAmount(orderDetail.getOriginAmount().subtract(orderDetail.getCouponReduce()));
        orderDetailService.save(orderDetail);

        // 更新优惠卷使用状态
        if (null != orderFormVO.getCouponUseId()) {
            couponInfoFeignClient.remoteUpdateCouponInfoUseState(orderFormVO.getCouponUseId(), orderInfo.getId());
        }

        return orderInfo.getId();
    }

    // 更新订单状态
    @Override
    public void updateOrderStatus(String out_trade_no) {
        //根据订单号查询订单
        LambdaQueryWrapper<OrderInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderInfo::getOutTradeNo, out_trade_no);
        OrderInfo orderInfo = baseMapper.selectOne(wrapper);
        //设置订单状态
        orderInfo.setOrderStatus("1");
        //调用方法更新
        baseMapper.updateById(orderInfo);
    }

    // 根据id获取订单详情
    @Override
    public OrderInfoVO getOrderInfoById(Long id) {
        //id查询订单基本信息和详情信息
        OrderInfo orderInfo = baseMapper.selectById(id);
        OrderDetail orderDetail = orderDetailService.getById(id);

        //封装OrderInfoVo
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        BeanUtils.copyProperties(orderInfo, orderInfoVO);
        orderInfoVO.setCourseId(orderDetail.getCourseId());
        orderInfoVO.setCourseName(orderDetail.getCourseName());
        return orderInfoVO;
    }

    // 获取订单详情
    private void getOrderDetail(OrderInfo orderInfo) {
        //订单id
        Long id = orderInfo.getId();
        //查询订单详情
        OrderDetail orderDetail = orderDetailService.getById(id);
        if (orderDetail != null) {
            String courseName = orderDetail.getCourseName();
            orderInfo.getParam().put("courseName", courseName);
        }
    }
}
