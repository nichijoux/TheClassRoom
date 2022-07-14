package com.zh.tcr.vod.service;

import com.zh.tcr.model.entity.vod.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
public interface CourseDescriptionService extends IService<CourseDescription> {
    // 根据课程id删除课程描述
    void deleteDescriptionByCourseId(Long courseId);
}
