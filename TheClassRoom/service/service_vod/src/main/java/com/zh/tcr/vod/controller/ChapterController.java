package com.zh.tcr.vod.controller;


import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.DataValidation;
import com.zh.tcr.model.entity.vod.Chapter;
import com.zh.tcr.model.vo.vod.ChapterVO;
import com.zh.tcr.vod.service.ChapterService;
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
 * 章节 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Api(tags = "课程章节管理")
@RestController
@RequestMapping("/service_vod/chapter")
public class ChapterController {
    private ChapterService chapterService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @ApiOperation(value = "获取大纲列表（章节、小节列表）")
    @GetMapping("getChapterAndVideoInfo/{courseId}")
    public Result getChapterAndVideoInfo(
            @ApiParam(name = "courseId", value = "要获取信息的课程id", required = true)
            @PathVariable Long courseId) {
        List<ChapterVO> chapters = chapterService.getChapterAndVideoInfo(courseId);
        return Result.ok().data("chapters", chapters);
    }

    @ApiOperation(value = "添加章节信息")
    @PostMapping("addChapterInfo")
    public Result addChapterInfo(
            @ApiParam(name = "chapter", value = "要添加的章节信息", required = true)
            @Valid @RequestBody Chapter chapter,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        return chapterService.save(chapter) ? Result.ok() : Result.error().message("添加章节失败");
    }

    @ApiOperation(value = "根据id获取章节信息")
    @GetMapping("getChapterInfo/{id}")
    public Result getChapterInfo(
            @ApiParam(name = "id", value = "章节id", required = true)
            @PathVariable Long id) {
        Chapter chapter = chapterService.getById(id);
        return Result.ok().data("chapter", chapter);
    }

    @ApiOperation(value = "修改章节信息")
    @PostMapping("updateChapterInfo")
    public Result updateChapterInfo(
            @ApiParam(name = "chapter", value = "章节信息", required = true)
            @Valid @RequestBody Chapter chapter,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        return chapterService.updateById(chapter) ?
                Result.ok() : Result.error().message("修改章节信息失败");
    }

    @ApiOperation(value = "删除章节,同时删除里面对应的小节视频")
    @DeleteMapping("deleteChapterInfo/{id}")
    public Result deleteChapterInfo(
            @ApiParam(name = "id", value = "要删除的章节id", required = true)
            @PathVariable Long id) {
        chapterService.deleteChapter(id);
        return Result.ok();
    }
}

