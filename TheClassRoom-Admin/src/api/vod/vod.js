import request from '@/utils/request'

const VOD_URL = 'service_vod/vod'

export default {
    // 删除视频
    deleteVideo(id) {
        return request({
            url: `${VOD_URL}/deleteVideo/${id}`,
            method: 'DELETE'
        })
    }
}