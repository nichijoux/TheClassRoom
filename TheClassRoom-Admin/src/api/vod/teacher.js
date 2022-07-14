import request from '@/utils/request'

const TEACHER_URL = 'service_vod/teacher'

export default {
    // 获取所有讲师信息
    getAllTeacherInfo() {
        return request({
            url: `${TEACHER_URL}/getAllTeacherInfo`,
            method: 'GET',
        })
    },
    // 条件分页查询讲师信息
    pageQueryTeacherInfo(index, limit, queryCondition) {
        return request({
            url: `${TEACHER_URL}/pageQueryTeacherInfo/${index}/${limit}`,
            method: 'POST',
            data: queryCondition
        })
    },
    // 根据id查询讲师信息
    getTeacherInfo(id) {
        return request({
            url: `${TEACHER_URL}/getTeacherInfo/${id}`,
            method: 'GET',
        })
    },
    // 添加讲师信息
    addTeacherInfo(teacher) {
        return request({
            url: `${TEACHER_URL}/addTeacherInfo`,
            method: 'POST',
            data: teacher
        })
    },
    // 更新讲师信息
    updateTeacherInfo(teacher) {
        return request({
            url: `${TEACHER_URL}/updateTeacherInfo`,
            method: 'POST',
            data: teacher
        })
    },
    // 根据id删除讲师信息
    deleteTeacherInfo(id) {
        return request({
            url: `${TEACHER_URL}/deleteTeacherInfo/${id}`,
            method: 'DELETE',
        })
    },
    // 批量删除讲师信息
    batchDeleteTeacherInfo(ids) {
        return request({
            url: `${TEACHER_URL}/batchDeleteTeacherInfo/`,
            method: 'DELETE',
            data: ids
        })
    }
}
