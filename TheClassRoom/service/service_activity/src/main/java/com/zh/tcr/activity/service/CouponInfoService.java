package com.zh.tcr.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.activity.CouponInfo;
import com.zh.tcr.model.entity.activity.CouponUse;
import com.zh.tcr.model.vo.activity.CouponUseQueryCondition;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
public interface CouponInfoService extends IService<CouponInfo> {
    // 条件分页查询优惠卷信息
    void pageQueryCouponInfo(Page<CouponUse> couponUsePage, CouponUseQueryCondition queryCondition);

    // 更新优惠券使用状态
    void updateCouponInfoUseState(Long couponId, Long orderId);
}
