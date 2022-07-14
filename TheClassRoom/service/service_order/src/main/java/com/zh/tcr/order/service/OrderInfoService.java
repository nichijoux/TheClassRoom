package com.zh.tcr.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.order.OrderInfo;
import com.zh.tcr.model.vo.order.OrderFormVO;
import com.zh.tcr.model.vo.order.OrderInfoQueryCondition;
import com.zh.tcr.model.vo.order.OrderInfoVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
public interface OrderInfoService extends IService<OrderInfo> {
    // 分页查询订单表信息
    void pageQueryOrderInfo(Page<OrderInfo> orderInfoPage,OrderInfoQueryCondition queryCondition);

    // 生成订单
    Long generateOrder(OrderFormVO orderFormVO);

    // 更新订单状态
    void updateOrderStatus(String out_trade_no);

    // 根据id获取订单信息
    OrderInfoVO getOrderInfoById(Long id);
}
