import request from '@/utils/request'

const LIVE_COURSE_URL = '/service_live/liveCourse'

export default {
    // 分页查询获取直播列表
    pageQueryLiveInfo(index, limit) {
        return request({
            url: `${LIVE_COURSE_URL}/pageQueryLiveInfo/${index}/${limit}`,
            method: 'GET'
        })
    },
    // 获取最近的直播
    getLatelyLiveList() {
        return request({
            url: `${LIVE_COURSE_URL}/getLatelyLiveList`,
            method: 'GET'
        })
    },
    // 获取直播课程信息
    getLiveCourse(id) {
        return request({
            url: `${LIVE_COURSE_URL}/getLiveCourse/${id}`,
            method: 'GET'
        })
    },
    // 获取直播账号信息
    getLiveCourseAccount(id) {
        return request({
            url: `${LIVE_COURSE_URL}/getLiveCourseAccount/${id}`,
            method: 'GET'
        })
    },
    // 添加直播课程
    addLiveCourse(liveCourse) {
        return request({
            url: `${LIVE_COURSE_URL}/addLiveCourse`,
            method: 'POST',
            data: liveCourse
        })
    },
    // 更新直播课程信息
    updateLiveCourse(liveCourse) {
        return request({
            url: `${LIVE_COURSE_URL}/updateLiveCourse`,
            method: 'POST',
            data: liveCourse
        })
    },
    // 删除直播课程
    deleteLiveCourse(id) {
        return request({
            url: `${LIVE_COURSE_URL}/deleteLiveCourse/${id}`,
            method: 'DELETE'
        })
    },
    // 批量删除直播课程
    removeRows(idList) {
        return request({
            url: `${LIVE_COURSE_URL}/batchRemove`,
            method: 'delete',
            data: idList
        })
    },
    // 获取直播配置信息
    getLiveCourseConfig(id) {
        return request({
            url: `${LIVE_COURSE_URL}/getLiveCourseConfig/${id}`,
            method: 'GET'
        })
    },
    // 修改配置
    updateLiveConfig(liveCourseConfigVO) {
        return request({
            url: `${LIVE_COURSE_URL}/updateLiveConfig`,
            method: 'POST',
            data: liveCourseConfigVO
        })
    },
}