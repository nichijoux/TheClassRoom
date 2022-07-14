import request from '@/utils/request'

const COURSE_URL = 'service_vod/course'

export default {
    // 获取所有课程信息
    getAllCourseInfo() {
        return request({
            url: `${COURSE_URL}/getAllCourseInfo`,
            method: 'GET'
        })
    },
    // 条件分页查询课程信息
    pageQueryCourseInfo(index, limit, queryCondition) {
        return request({
            url: `${COURSE_URL}/pageQueryCourseInfo/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 根据id获取课程信息
    getCourseInfo(id) {
        return request({
            url: `${COURSE_URL}/getCourseInfo/${id}`,
            method: 'GET',
        })
    },
    // 添加课程信息
    addCourseInfo(course) {
        return request({
            url: `${COURSE_URL}/addCourseInfo`,
            method: 'POST',
            data: course,
        })
    },
    // 修改课程信息
    updateCourseInfo(course) {
        return request({
            url: `${COURSE_URL}/updateCourseInfo`,
            method: 'POST',
            data: course,
        })
    },
    // 根据课程id查询发布课程信息
    getCoursePublishInfo(id) {
        return request({
            url: `${COURSE_URL}/getCoursePublishInfo/${id}`,
            method: 'GET',
        })
    },
    // 根据id发布课程
    publishCourse(id) {
        return request({
            url: `${COURSE_URL}/publishCourse/${id}`,
            method: 'POST',
        })
    },
    // 根据id删除课程信息
    deleteCourseInfo(id) {
        return request({
            url: `${COURSE_URL}/deleteCourseInfo/${id}`,
            method: 'DELETE',
        })
    }
}