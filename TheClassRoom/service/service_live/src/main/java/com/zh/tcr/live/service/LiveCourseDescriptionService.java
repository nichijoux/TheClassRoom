package com.zh.tcr.live.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.live.LiveCourseDescription;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseDescriptionService extends IService<LiveCourseDescription> {

    LiveCourseDescription getLiveCourseById(Long id);
}
