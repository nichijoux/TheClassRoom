import request from '@/utils/request'

const VIDEO_URL = 'service_vod/video'

export default {
    // 根据id获取视频信息
    getVideoInfo(id) {
        return request({
            url: `${VIDEO_URL}/getVideoInfo/${id}`,
            method: 'GET'
        })
    },
    // 添加视频信息
    addVideoInfo(video) {
        return request({
            url: `${VIDEO_URL}/addVideoInfo`,
            method: 'POST',
            data: video
        })
    },
    // 更新视频信息
    updateVideoInfo(video) {
        return request({
            url: `${VIDEO_URL}/updateVideoInfo`,
            method: 'POST',
            data: video
        })
    },
    // 根据id删除视频信息
    deleteVideoInfo(id) {
        return request({
            url: `${VIDEO_URL}/deleteVideoInfo/${id}`,
            method: 'DELETE',
        })
    }
}