package com.zh.tcr.activity.controller;


import com.zh.tcr.activity.service.CouponUseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券领用表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Api(tags = "优惠券领用表管理")
@RestController
@RequestMapping("/service_activity/coupon_use")
public class CouponUseController {
    private CouponUseService couponUseService;

    @Autowired
    public void setCouponUseService(CouponUseService couponUseService) {
        this.couponUseService = couponUseService;
    }
}

