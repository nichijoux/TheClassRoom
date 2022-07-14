package com.zh.tcr.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.model.entity.vod.Course;
import com.zh.tcr.model.entity.vod.CourseDescription;
import com.zh.tcr.model.entity.vod.Subject;
import com.zh.tcr.model.entity.vod.Teacher;
import com.zh.tcr.model.vo.vod.*;
import com.zh.tcr.vod.mapper.CourseMapper;
import com.zh.tcr.vod.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    // 讲师服务
    private TeacherService teacherService;

    // 科目服务
    private SubjectService subjectService;

    // 章节服务
    private ChapterService chapterService;

    // 课程描述服务
    private CourseDescriptionService descriptionService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setDescriptionService(CourseDescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    // 获取所有课程信息
    @Override
    public List<Course> getAllCourseInfo() {
        List<Course> list = baseMapper.selectList(null);
        list.forEach(this::getNameById);
        return list;
    }

    // 条件分页查询课程信息
    @Override
    public void pageQueryCourseInfo(Page<Course> coursePage, CourseQueryCondition queryCondition) {
        if (queryCondition == null) {
            baseMapper.selectPage(coursePage, null);
            return;
        }
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        // 获取查询条件
        String title = queryCondition.getTitle();
        Long teacherId = queryCondition.getTeacherId();
        Long subjectId = queryCondition.getSubjectId();// 学科二级分类
        Long subjectParentId = queryCondition.getSubjectParentId();// 学科一级分类
        // 设定查询条件
        if (!StringUtils.isEmpty(title)) wrapper.like("title", title);
        if (!StringUtils.isEmpty(teacherId)) wrapper.eq("teacher_id", teacherId);
        if (!StringUtils.isEmpty(subjectId)) wrapper.eq("subject_id", subjectId);
        if (!StringUtils.isEmpty(subjectParentId)) wrapper.eq("subject_parent_id", subjectParentId);
        // 查询数据库
        baseMapper.selectPage(coursePage, wrapper);
        // 还需要设置讲师、课程名称这些，最好是写sql来完成，因为数据不多，因此这里直接多次查询数据库
        // TODO:自写sql
        List<Course> courses = coursePage.getRecords();
        courses.forEach(this::getNameById);
    }

    // 根据id获取课程信息
    @Override
    public CourseFormVO getCourseInfo(Long id) {
        // 课程基本信息
        Course course = baseMapper.selectById(id);
        if (course == null) {
            throw new TCRException(20001, "课程不存在");
        }
        // 课程描述信息
        CourseDescription courseDescription = descriptionService.getById(id);
        // 封装到courseFormVO
        CourseFormVO courseFormVO = new CourseFormVO();
        BeanUtils.copyProperties(course, courseFormVO);
        if (courseDescription != null)
            courseFormVO.setDescription(courseDescription.getDescription());
        return courseFormVO;
    }

    // 添加课程基本信息
    @Override
    public Long addCourseInfo(CourseFormVO courseFormVO) {
        // 设置课程基本信息,对应course表
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVO, course);
        baseMapper.insert(course);
        // 添加课程描述信息,对应course_description表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVO.getDescription());
        // 设置课程id
        courseDescription.setId(course.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }

    // 修改课程信息
    @Override
    public void updateCourseInfo(CourseFormVO courseFormVO) {
        // 修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVO, course);
        baseMapper.updateById(course);
        // 修改课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVO.getDescription());
        // 设置课程描述id
        courseDescription.setId(course.getId());
        descriptionService.updateById(courseDescription);
    }

    // 根据课程id查询发布课程信息
    @Override
    public CoursePublishVO getCoursePublishInfo(Long id) {
        return baseMapper.getCoursePublishVOById(id);
    }

    // 根据id发布课程
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }

    // 根据课程id删除课程信息
    @Override
    public void deleteCourseInfo(Long id) {
        // 根据课程id删除章节,小节也会同时被删除
        chapterService.deleteChapterByCourseId(id);
        // 删除课程描述
        descriptionService.deleteDescriptionByCourseId(id);
        // 根据课程id删除课程
        baseMapper.deleteById(id);
    }

    // 用户端获取课程信息
    @Override
    public Map<String, Object> userGetCourseInfo(Long courseId) {
        //view_count浏览数量 +1
        Course course = baseMapper.selectById(courseId);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);

        //根据课程id查询
        //课程详情数据
        CourseVO courseVO = baseMapper.getCourseVOById(courseId);
        //课程章节小节数据
        List<ChapterVO> chapterVoList = chapterService.getChapterAndVideoInfo(courseId);
        //课程描述信息
        CourseDescription courseDescription = descriptionService.getById(courseId);
        //课程所属讲师信息
        Teacher teacher = teacherService.getById(course.getTeacherId());

        //封装map集合，返回
        Map<String, Object> map = new HashMap<>();
        map.put("courseVo", courseVO);
        map.put("chapterVoList", chapterVoList);
        map.put("description", null != courseDescription ? courseDescription.getDescription() : "");
        map.put("teacher", teacher);
        map.put("isBuy", false);//是否购买
        return map;
    }

    //获取这些id对应名称，进行封装，最终显示
    private void getNameById(Course course) {
        //根据讲师id获取讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher != null)
            course.getParam().put("teacherName", teacher.getName());

        //根据课程分类id获取课程分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (subjectOne != null)
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());

        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if (subjectTwo != null)
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
    }
}
