import request from '@/utils/request'

const CHAPTER_URL = 'service_vod/chapter'

export default {
    // 获取大纲列表（章节、小节列表）
    getChapterAndVideoInfo(courseId) {
        return request({
            url: `${CHAPTER_URL}/getChapterAndVideoInfo/${courseId}`,
            method: 'GET'
        })
    },
    // 添加章节信息
    addChapterInfo(chapter) {
        return request({
            url: `${CHAPTER_URL}/addChapterInfo/`,
            method: 'POST',
            data: chapter
        })
    },
    // 根据id获取章节信息
    getChapterInfo(id) {
        return request({
            url: `${CHAPTER_URL}/getChapterInfo/${id}`,
            method: 'GET',
        })
    },
    // 更新章节信息
    updateChapterInfo(chapter) {
        return request({
            url: `${CHAPTER_URL}/updateChapterInfo`,
            method: 'POST',
            data: chapter
        })
    },
    // 根据章节id删除章节
    deleteChapterInfo(id) {
        return request({
            url: `${CHAPTER_URL}/deleteChapterInfo/${id}`,
            method: 'DELETE'
        })
    }
}