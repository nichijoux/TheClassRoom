import request from '@/utils/request'

const ORDER_URL = 'service_order/'

export default {
    // 订单支付
    createJsapi(orderNo) {
        return request({
            url: `${ORDER_URL}/wxPay/createJsapi/${orderNo}`,
            method: 'get'
        })
    },

    // 订单信息
    getOrderInfo(orderId) {
        return request({
            url: `${ORDER_URL}/order_info/getOrderInfo/${orderId}`,
            method: 'get'
        })
    },
    // 生成订单
    generateOrder(orderFormVO) {
        return request({
            url: `${ORDER_URL}/order_info/generateOrder`,
            method: 'post',
            data: orderFormVO
        })
    },
    // 查询订单状态
    queryPayStatus(orderNo) {
        return request({
            url: `${ORDER_URL}/wxPay/queryPayStatus/${orderNo}`,
            method: 'get'
        })
    },
    // 分页查询订单信息
    pageQueryOrderInfo(index, limit) {
        return request({
            url: `${ORDER_URL}/order_info/${index}/${limit}`,
            method: 'POST'
        })
    },
}
