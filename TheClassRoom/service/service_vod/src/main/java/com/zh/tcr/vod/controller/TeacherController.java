package com.zh.tcr.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.DataValidation;
import com.zh.tcr.model.entity.vod.Teacher;
import com.zh.tcr.model.vo.vod.TeacherQueryCondition;
import com.zh.tcr.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@Api(tags = "讲师控制器")
@RestController
@RequestMapping("/service_vod/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "获取所有讲师信息")
    @GetMapping("getAllTeacherInfo")
    public Result getAllTeacherInfo() {
        List<Teacher> teachers = teacherService.list(null);
        return Result.ok().data("teachers", teachers);
    }

    @ApiOperation(value = "条件分页查询讲师信息")
    @PostMapping("pageQueryTeacherInfo/{index}/{limit}")
    public Result pageQueryTeacherInfo(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "queryCondition", value = "前端需要传入的条件")
            @RequestBody(required = false) TeacherQueryCondition queryCondition) {
        Page<Teacher> teacherPage = new Page<>(index, limit);
        teacherService.pageQueryTeacherInfo(teacherPage, queryCondition);
        return Result.ok()
                .data("total", teacherPage.getTotal())
                .data("teachers", teacherPage.getRecords());
    }

    @ApiOperation(value = "根据讲师id获取讲师信息")
    @GetMapping("getTeacherInfo/{id}")
    public Result getTeacherInfo(
            @ApiParam(name = "id", value = "要查询的讲师id", required = true)
            @PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "添加讲师信息")
    @PostMapping("addTeacherInfo")
    public Result addTeacherInfo(
            @ApiParam(name = "teacher", value = "要添加的讲师信息", required = true)
            @Valid @RequestBody Teacher teacher,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        return teacherService.save(teacher) ? Result.ok() : Result.error().message("添加讲师失败");
    }

    @ApiOperation(value = "更新讲师信息")
    @PostMapping("updateTeacherInfo")
    public Result updateTeacherInfo(
            @ApiParam(name = "teacher", value = "要修改的讲师信息", required = true)
            @Valid @RequestBody Teacher teacher,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        return teacherService.updateById(teacher) ? Result.ok() : Result.error().message("修改讲师失败");
    }

    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("deleteTeacherInfo/{id}")
    public Result deleteTeacherInfo(
            @ApiParam(name = "id", value = "ID", required = true)
            @PathVariable Long id) {
        return teacherService.removeById(id) ? Result.ok() : Result.error();
    }

    @ApiOperation(value = "根据id批量删除讲师信息")
    @DeleteMapping("batchDeleteTeacherInfo")
    public Result batchDeleteTeacherInfo(
            @ApiParam(name = "ids", value = "要删除的讲师id集合", required = true)
            @RequestBody List<Long> ids) {
        return teacherService.removeByIds(ids) ? Result.ok() : Result.error();
    }

    @ApiOperation(value = "远程调用,根据id查询讲师信息")
    @GetMapping("remoteGetTeacherInfo/{id}")
    public Teacher remoteGetTeacherInfo(
            @ApiParam(name = "id", value = "要查询的讲师id", required = true)
            @PathVariable Long id) {
        return teacherService.getById(id);
    }
}

