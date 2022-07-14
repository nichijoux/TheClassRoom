package com.zh.tcr.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.model.entity.order.OrderDetail;
import com.zh.tcr.order.mapper.OrderDetailMapper;
import com.zh.tcr.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
