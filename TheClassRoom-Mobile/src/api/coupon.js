import request from '@/utils/request'

const COUPON_URL = '/service_activity/couponInfo'

export default {

  findCouponInfo() {
    return request({
      url: `${COUPON_URL}/findCouponInfo`,
      method: 'GET'
    })
  },
  // 分页查询优惠券信息
  pageQueryCouponInfo(index, limit) {
    return request({
      url: `${COUPON_URL}/pageQueryCouponInfo/${index}/${limit}`,
      method: 'GET'
    })
  }
}
