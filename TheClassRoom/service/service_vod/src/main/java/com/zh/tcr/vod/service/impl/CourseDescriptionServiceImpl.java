package com.zh.tcr.vod.service.impl;

import com.zh.tcr.model.entity.vod.CourseDescription;
import com.zh.tcr.vod.mapper.CourseDescriptionMapper;
import com.zh.tcr.vod.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {
    // 根据课程id删除课程描述
    @Override
    public void deleteDescriptionByCourseId(Long courseId) {
        baseMapper.deleteById(courseId);
    }
}
