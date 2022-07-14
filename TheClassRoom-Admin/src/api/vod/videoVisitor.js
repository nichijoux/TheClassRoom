import request from '@/utils/request'

const VIDEO_VISITOR_URL = 'service_vod/videoVisitor'

export default {
    viewCount(courseId, startDate, endDate) {
        return request({
            url: `${VIDEO_VISITOR_URL}/viewCount/${courseId}/${startDate}/${endDate}`,
            method: 'GET'
        })
    }
}