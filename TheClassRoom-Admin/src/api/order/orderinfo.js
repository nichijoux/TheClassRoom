import request from '@/utils/request'

const ORDER_INFO_URL = 'service_order/order_info'

export default {
    pageQueryOrderInfo(index, limit, queryCondition) {
        return request({
            url: `${ORDER_INFO_URL}/pageQueryOrderInfo/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    }
}