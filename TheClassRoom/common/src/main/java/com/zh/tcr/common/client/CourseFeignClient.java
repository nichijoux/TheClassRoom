package com.zh.tcr.common.client;

import com.zh.tcr.model.entity.vod.Course;
import com.zh.tcr.model.entity.vod.Teacher;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "service-vod")
public interface CourseFeignClient {

    @ApiOperation(value = "远程调用,根据课程id查询课程")
    @GetMapping("/service_vod/course/remoteGetCourse/{courseId}")
    Course remoteGetCourse(@PathVariable("courseId") Long courseId);

    @ApiOperation("根据关键字查询课程")
    @GetMapping("/service_vod/course/remoteGetCourseListByKeyword/{keyword}")
    List<Course> remoteGetCourseListByKeyword(
            @ApiParam(value = "关键字", required = true)
            @PathVariable("keyword") String keyword);

    @ApiOperation(value = "远程调用,根据id查询讲师信息")
    @GetMapping("/service_vod/teacher/remoteGetTeacherInfo/{id}")
    Teacher remoteGetTeacherInfo(
            @ApiParam(name = "id", value = "要查询的讲师id", required = true)
            @PathVariable("id") Long id);
}
