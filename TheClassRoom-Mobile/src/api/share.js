import request from '@/utils/request'

const SHARE_URL = 'service_wechat/share'

export default {
  // 获取签名
  getSignature(url) {
    return request({
      url: `${SHARE_URL}/getSignature?url=${url}`,
      method: 'GET'
    })
  }
}