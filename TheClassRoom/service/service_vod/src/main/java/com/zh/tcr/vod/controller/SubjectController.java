package com.zh.tcr.vod.controller;


import com.zh.tcr.common.result.Result;
import com.zh.tcr.model.entity.vod.Subject;
import com.zh.tcr.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@Api(tags = "课程科目控制器")
@RestController
@RequestMapping("/service_vod/subject")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation("根据id获取其子课程分类列表,便于懒加载,第一层的parent_id为0")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(
            @ApiParam(name = "id", value = "父分类id", required = true)
            @PathVariable Long id) {
        List<Subject> list = subjectService.getChildSubject(id);
        return Result.ok().data("list", list);
    }

    @ApiOperation("课程分类导出")
    @GetMapping("exportSubject")
    public void exportSubject(HttpServletResponse response) {
        subjectService.exportSubject(response);
    }

    @ApiOperation("课程分类导入")
    @PostMapping("importSubject")
    public Result importSubject(
            @ApiParam(name = "file", value = "要上传的excel文件", required = true)
                    MultipartFile file) {
        // 原始文件名称
        String fileName = file.getOriginalFilename();
        if (fileName == null) return Result.error().message("文件错误");

        // 解析到文件后缀，判断是否合法
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index + 1);
        if (index == -1 || suffix.isEmpty()) {
            return Result.error().message("文件后缀不能为空");
        }

        // 允许上传的文件后缀列表
        Set<String> allowSuffix = new HashSet<>(Arrays.asList("xlsx", "xls"));
        if (!allowSuffix.contains(suffix.toLowerCase())) {
            return Result.error().message("非法的文件，不允许的文件类型：" + suffix);
        }

        // 保存文件
        subjectService.importSubject(file);
        return Result.ok();
    }
}

