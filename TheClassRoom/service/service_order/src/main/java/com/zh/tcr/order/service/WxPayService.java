package com.zh.tcr.order.service;

import java.util.Map;

public interface WxPayService {
    // 创建微信支付
    Map<String, Object> createJsapi(String orderNo);

    // 查询订单状态
    Map<String, String> queryPayStatus(String orderNo);
}
