package com.zh.tcr.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
public interface VideoVisitorService extends IService<VideoVisitor> {
    // 根据课程id和开始时间、结束时间查询查看视频的数据
    Map<String, Object> viewCount(Long courseId, String startDate, String endDate);
}
