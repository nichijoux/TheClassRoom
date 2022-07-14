import request from '@/utils/request'

const SMS_URL = 'service_sms/sms'

export default {
  // 添加购物车
  sendVerificationCode(phone) {
    return request({
      url: `${SMS_URL}/sendVerificationCode/${phone}`,
      method: 'get'
    })
  }
}
