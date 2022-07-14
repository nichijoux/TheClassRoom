package com.zh.tcr.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.DataValidation;
import com.zh.tcr.model.entity.vod.Course;
import com.zh.tcr.model.vo.vod.CourseFormVO;
import com.zh.tcr.model.vo.vod.CoursePublishVO;
import com.zh.tcr.model.vo.vod.CourseQueryCondition;
import com.zh.tcr.vod.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/service_vod/course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "获取所有课程信息")
    @GetMapping("getAllCourseInfo")
    public Result getAllCourseInfo() {
        List<Course> courses = courseService.getAllCourseInfo();
        return Result.ok().data("courses", courses);
    }

    @ApiOperation(value = "条件分页查询课程信息")
    @PostMapping("pageQueryCourseInfo/{index}/{limit}")
    public Result pageQueryCourseInfo(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "queryCondition", value = "前端需要传入的条件")
            @RequestBody(required = false) CourseQueryCondition queryCondition) {
        Page<Course> coursePage = new Page<>(index, limit);
        courseService.pageQueryCourseInfo(coursePage, queryCondition);
        return Result.ok().data("total", coursePage.getTotal())
                .data("courses", coursePage.getRecords())
                .data("totalPages",coursePage.getSize());
    }

    @ApiOperation("根据课程分类分页查询课程列表")
    @GetMapping("pageQueryCourseInfo/{subjectParentId}/{index}/{limit}")
    public Result pageQueryCourseInfo(
            @ApiParam(value = "课程一级分类ID", required = true)
            @PathVariable Long subjectParentId,
            @ApiParam(name = "index", value = "当前页码", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //封装条件
        CourseQueryCondition queryCondition = new CourseQueryCondition();
        queryCondition.setSubjectParentId(subjectParentId);
        //创建page对象
        Page<Course> coursePage = new Page<>(index, limit);
        courseService.pageQueryCourseInfo(coursePage, queryCondition);
        return Result.ok().data("courses", coursePage.getRecords())
                .data("total", coursePage.getTotal());
    }

    @ApiOperation(value = "根据id获取课程信息")
    @GetMapping("getCourseInfo/{id}")
    public Result getCourseInfo(
            @ApiParam(name = "id", value = "要查询的课程id", required = true)
            @PathVariable Long id) {
        CourseFormVO course = courseService.getCourseInfo(id);
        return Result.ok().data("course", course);
    }

    @ApiOperation(value = "用户端获取课程信息")
    @GetMapping("userGetCourseInfo/{id}")
    public Result userGetCourseInfo(
            @ApiParam(name = "id", value = "要查询的课程id", required = true)
            @PathVariable Long id) {
        Map<String, Object> map = courseService.userGetCourseInfo(id);
        return Result.ok().data("map", map);
    }

    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(
            @ApiParam(name = "courseFormVO", value = "课程信息VO", required = true)
            @Valid @RequestBody CourseFormVO courseFormVO,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        Long courseId = courseService.addCourseInfo(courseFormVO);
        return Result.ok().data("courseId", courseId);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(
            @ApiParam(name = "courseFormVO", value = "课程信息", required = true)
            @Valid @RequestBody CourseFormVO courseFormVO,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        courseService.updateCourseInfo(courseFormVO);
        return Result.ok().data("courseId", courseFormVO.getId());
    }

    @ApiOperation(value = "根据课程id查询发布课程信息")
    @GetMapping("getCoursePublishInfo/{id}")
    public Result getCoursePublishInfo(
            @ApiParam(name = "id", value = "要查询的课程id", required = true)
            @PathVariable Long id) {
        CoursePublishVO coursePublishVO = courseService.getCoursePublishInfo(id);
        return Result.ok().data("coursePublishVO", coursePublishVO);
    }

    @ApiOperation(value = "课程最终发布")
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id删除课程信息")
    @DeleteMapping("deleteCourseInfo/{id}")
    public Result deleteCourseInfo(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable Long id) {
        courseService.deleteCourseInfo(id);
        return Result.ok();
    }

    @ApiOperation(value = "远程调用,根据课程id查询课程")
    @GetMapping("remoteGetCourse/{courseId}")
    public Course remoteGetCourse(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId) {
        return courseService.getById(courseId);
    }

    @ApiOperation("根据关键字查询课程")
    @GetMapping("remoteGetCourseListByKeyword/{keyword}")
    public List<Course> remoteGetCourseListByKeyword(
            @ApiParam(value = "关键字", required = true)
            @PathVariable String keyword) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.like("title", keyword);
        return courseService.list(wrapper);
    }
}

