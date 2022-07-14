package com.zh.tcr.live.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.live.LiveCourseConfig;

/**
 * <p>
 * 直播课程配置表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseConfigService extends IService<LiveCourseConfig> {
    // 根据课程id获取直播信息
    LiveCourseConfig getCourseConfigByCourseId(Long id);
}
