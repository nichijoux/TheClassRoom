package com.zh.tcr.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.vod.Course;
import com.zh.tcr.model.vo.vod.CourseFormVO;
import com.zh.tcr.model.vo.vod.CoursePublishVO;
import com.zh.tcr.model.vo.vod.CourseQueryCondition;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
public interface CourseService extends IService<Course> {
    // 获取所有课程信息
    List<Course> getAllCourseInfo();

    // 条件分页查询课程信息
    void pageQueryCourseInfo(Page<Course> coursePage, CourseQueryCondition queryCondition);

    // 根据id获取课程信息
    CourseFormVO getCourseInfo(Long id);

    // 添加课程基本信息
    Long addCourseInfo(CourseFormVO courseFormVO);

    // 修改课程信息
    void updateCourseInfo(CourseFormVO courseFormVO);

    // 根据id查询发布课程信息
    CoursePublishVO getCoursePublishInfo(Long id);

    // 根据id发布课程信息
    void publishCourse(Long id);

    // 根据课程id删除课程信息
    void deleteCourseInfo(Long id);

    // 用户端获取课程信息
    Map<String, Object> userGetCourseInfo(Long id);
}
