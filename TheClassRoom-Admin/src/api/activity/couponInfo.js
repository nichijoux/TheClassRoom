import request from '@/utils/request'

const COUPON_INFO_URL = '/service_activity/couponInfo'

export default {
    // 分页查询优惠卷信息
    pageQueryCouponInfo(index, limit) {
        return request({
            url: `${COUPON_INFO_URL}/pageQueryCouponInfo/${index}/${limit}`,
            method: 'GET'
        })
    },
    // 根据id获取优惠券信息
    getCouponInfo(id) {
        return request({
            url: `${COUPON_INFO_URL}/getCouponInfo/${id}`,
            method: 'GET'
        })
    },
    // 增加新的优惠券
    addCouponInfo(couponInfo) {
        return request({
            url: `${COUPON_INFO_URL}/addCouponInfo`,
            method: 'POST',
            data: couponInfo
        })
    },
    // 修改已有的优惠卷信息
    updateCouponInfo(couponInfo) {
        return request({
            url: `${COUPON_INFO_URL}/updateCouponInfo`,
            method: 'POST',
            data: couponInfo
        })
    },
    // 根据id删除已经有的优惠卷信息
    deleteConponInfo(id) {
        return request({
            url: `${COUPON_INFO_URL}/deleteConponInfo/${id}`,
            method: 'DELETE'
        })
    },
    // 批量删除优惠卷信息
    batchDeleteCouponInfo(ids) {
        return request({
            url: `${COUPON_INFO_URL}/batchDeleteCouponInfo`,
            method: 'DELETE',
            data: ids
        })
    },
    // 分页查询已经使用过的优惠卷信息
    pageQueryCouponUseInfo(index, limit, queryCondition) {
        return request({
            url: `${COUPON_INFO_URL}/pageQueryCouponUseInfo/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    }
}