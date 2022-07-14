package com.zh.tcr.vod.service;

import com.zh.tcr.model.entity.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.vo.vod.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
public interface ChapterService extends IService<Chapter> {
    // 获取大纲列表（包括章节和小节）
    List<ChapterVO> getChapterAndVideoInfo(Long courseId);

    // 根据章节id,删除章节,同时删除里面对应的小节视频
    void deleteChapter(Long id);

    // 根据课程id删除章节
    void deleteChapterByCourseId(Long courseId);
}
