package com.zh.tcr.vod.controller;


import com.zh.tcr.common.result.Result;
import com.zh.tcr.model.entity.vod.Video;
import com.zh.tcr.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Api(tags = "课程视频管理")
@RestController
@RequestMapping("/service_vod/video")
public class VideoController {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation(value = "根据id获取视频信息")
    @GetMapping("getVideoInfo/{id}")
    public Result getVideoInfo(
            @ApiParam(name = "id", value = "视频id", required = true)
            @PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.ok().data("video", video);
    }

    @ApiOperation(value = "增添视频信息")
    @PostMapping("addVideoInfo")
    public Result addVideoInfo(
            @ApiParam(name = "video", value = "要添加的视频信息", required = true)
            @RequestBody Video video) {
        return videoService.save(video) ? Result.ok() : Result.error().message("添加视频信息失败");
    }

    @ApiOperation(value = "更新视频信息")
    @PostMapping("updateVideoInfo")
    public Result updateVideoInfo(
            @ApiParam(name = "video", value = "视频信息", required = true)
            @RequestBody Video video) {
        boolean flag = videoService.updateById(video);
        return flag ? Result.ok() : Result.error().message("更新视频信息失败");
    }

    @ApiOperation(value = "删除视频信息")
    @DeleteMapping("deleteVideoInfo/{id}")
    public Result deleteVideoInfo(
            @ApiParam(name = "id", value = "要删除的视频id", required = true)
            @PathVariable Long id) {
        videoService.deleteVideoInfo(id);
        return Result.ok();
    }
}

