package com.zh.tcr.vod.service;

import com.zh.tcr.model.entity.vod.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
public interface VideoService extends IService<Video> {
    // 根据id删除视频信息
    void deleteVideoInfo(Long id);

    // 根据章节id删除视频
    void deleteVideoByChapterId(Long chapterId);
}
