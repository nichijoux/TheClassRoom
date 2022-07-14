import request from '@/utils/request'

const VOD_URL = 'service_vod/vod'

export default {
  // 获取播放凭证
  getPlayAuth(courseId, videoId) {
    return request({
      url: `${VOD_URL}/getPlayAuth/${courseId}/${videoId}`,
      method: 'get'
    })
  }
}
