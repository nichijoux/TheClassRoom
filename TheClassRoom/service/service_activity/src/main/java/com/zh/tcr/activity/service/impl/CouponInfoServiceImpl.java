package com.zh.tcr.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.activity.mapper.CouponInfoMapper;
import com.zh.tcr.activity.service.CouponInfoService;
import com.zh.tcr.activity.service.CouponUseService;
import com.zh.tcr.common.client.UserInfoFeignClient;
import com.zh.tcr.model.entity.activity.CouponInfo;
import com.zh.tcr.model.entity.activity.CouponUse;
import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.model.vo.activity.CouponUseQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
    private CouponUseService couponUseService;

    private UserInfoFeignClient userInfoFeignClient;

    @Autowired
    public void setCouponUseService(CouponUseService couponUseService) {
        this.couponUseService = couponUseService;
    }

    @Autowired
    public void setUserInfoFeignClient(UserInfoFeignClient userInfoFeignClient) {
        this.userInfoFeignClient = userInfoFeignClient;
    }

    // 条件分页查询
    @Override
    public void pageQueryCouponInfo(Page<CouponUse> couponUsePage, CouponUseQueryCondition queryCondition) {
        if (queryCondition == null) {
            couponUseService.page(couponUsePage, null);
            return;
        }
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        // 获取查询条件
        Long couponId = queryCondition.getCouponId();
        String couponStatus = queryCondition.getCouponStatus();
        String getTimeBegin = queryCondition.getGetTimeBegin();
        String getTimeEnd = queryCondition.getGetTimeEnd();
        // 设定查询条件
        if (!StringUtils.isEmpty(couponId)) wrapper.eq("coupon_id", couponId);
        if (!StringUtils.isEmpty(couponStatus)) wrapper.eq("coupon_status", couponStatus);
        if (!StringUtils.isEmpty(getTimeBegin)) wrapper.ge("get_time", getTimeBegin);
        if (!StringUtils.isEmpty(getTimeEnd)) wrapper.le("get_time", getTimeEnd);
        // 查询
        couponUseService.page(couponUsePage, wrapper);
        couponUsePage.getRecords().forEach(this::getUserInfoById);
    }

    // 更新优惠券使用状态
    @Override
    public void updateCouponInfoUseState(Long couponId, Long orderId) {
        CouponUse couponUse = new CouponUse();
        couponUse.setId(couponId);
        couponUse.setOrderId(orderId);
        couponUse.setCouponStatus("1");
        couponUse.setUsingTime(new Date());
        couponUseService.updateById(couponUse);
    }

    // 根据用户id，通过远程调用得到用户信息
    private void getUserInfoById(CouponUse couponUse) {
        //获取用户id
        Long userId = couponUse.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            //远程调用
            UserInfo userInfo = userInfoFeignClient.remoteGetUserInfo(userId);
            if (userInfo != null) {
                couponUse.getParam().put("nickName", userInfo.getNickName());
                couponUse.getParam().put("phone", userInfo.getPhone());
            }
        }
    }
}
