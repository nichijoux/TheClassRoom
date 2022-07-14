package com.zh.tcr.vod.controller;


import com.zh.tcr.common.result.Result;
import com.zh.tcr.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Api(tags = "视频观看统计数据表")
@RestController
@RequestMapping("/service_vod/videoVisitor")
public class VideoVisitorController {
    private VideoVisitorService videoVisitorService;

    @Autowired
    public void setVideoVisitorService(VideoVisitorService videoVisitorService) {
        this.videoVisitorService = videoVisitorService;
    }

    @ApiOperation(value = "观看视频的统计数据接口")
    @GetMapping("viewCount/{courseId}/{startDate}/{endDate}")
    public Result viewCount(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "startDate", value = "开始时间", required = true)
            @PathVariable String startDate,
            @ApiParam(name = "endDate", value = "结束时间", required = true)
            @PathVariable String endDate) {
        Map<String, Object> map =
                videoVisitorService.viewCount(courseId, startDate, endDate);
        return Result.ok().data("viewCount", map);
    }
}

