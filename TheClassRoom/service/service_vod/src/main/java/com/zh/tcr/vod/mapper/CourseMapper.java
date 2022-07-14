package com.zh.tcr.vod.mapper;

import com.zh.tcr.model.entity.vod.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.tcr.model.vo.vod.CoursePublishVO;
import com.zh.tcr.model.vo.vod.CourseVO;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
public interface CourseMapper extends BaseMapper<Course> {
    // 根据课程id查询发布课程信息
    CoursePublishVO getCoursePublishVOById(Long id);

    // 根据课程id查询课程详情
    CourseVO getCourseVOById(Long id);
}
