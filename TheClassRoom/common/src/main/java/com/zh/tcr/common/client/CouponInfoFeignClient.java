package com.zh.tcr.common.client;

import com.zh.tcr.model.entity.activity.CouponInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-activity")
public interface CouponInfoFeignClient {
    @ApiOperation(value = "根据优惠券id获取优惠券信息")
    @GetMapping("service_activity/couponInfo/remoteGetCouponInfo/{couponId}")
    CouponInfo remoteGetCouponInfo(
            @ApiParam(name = "couponId", value = "优惠券id", required = true)
            @PathVariable("couponId") Long couponId);

    @ApiOperation(value = "根据id更新优惠券使用状态")
    @GetMapping("service_activity/couponInfo/remoteUpdateCouponInfoUseState/{couponId}/{orderId}")
    Boolean remoteUpdateCouponInfoUseState(
            @PathVariable("couponId") Long couponId,
            @PathVariable("orderId") Long orderId);
}
