import request from '@/utils/request'

const COURSE_URL = '/service_vod/course'

export default {

  // 课程分页列表
  pageQueryCourseInfo(subjectParentId, index, limit) {
    return request({
      url: `${COURSE_URL}/pageQueryCourseInfo/${subjectParentId}/${index}/${limit}`,
      method: 'GET'
    })
  },
  // 课程详情
  userGetCourseInfo(courseId) {
    return request({
      url: `${COURSE_URL}/userGetCourseInfo/${courseId}`,
      method: 'GET'
    })
  }
}
