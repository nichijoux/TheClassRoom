import request from '@/utils/request'

const SUBJECT_URL = '/service_vod/subject'

export default {
    //课程分类列表
    getChildSubject(id) {
        return request({
            url: `${SUBJECT_URL}/getChildSubject/${id}`,
            method: 'get'
        })
    },
}