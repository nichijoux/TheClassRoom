import request from '@/utils/request'

const LIVE_URL = '/service_live/liveCourse'

export default {
  // 直播详情
  userGetLiveCourseInfo(liveCourseId) {
    return request({
      url: `${LIVE_URL}/userGetLiveCourseInfo/${liveCourseId}`,
      method: 'get'
    })
  },
  // 获取accessToken
  getAccessToken(liveCourseId) {
    return request({
      url: `${LIVE_URL}/getAccessToken/${liveCourseId}`,
      method: 'get'
    })
  }
}
