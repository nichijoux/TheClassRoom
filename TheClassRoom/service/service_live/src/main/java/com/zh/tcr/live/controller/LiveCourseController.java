package com.zh.tcr.live.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.AuthContextHolder;
import com.zh.tcr.live.service.LiveCourseService;
import com.zh.tcr.model.entity.live.LiveCourse;
import com.zh.tcr.model.entity.live.LiveCourseAccount;
import com.zh.tcr.model.vo.live.LiveCourseConfigVO;
import com.zh.tcr.model.vo.live.LiveCourseFormVO;
import com.zh.tcr.model.vo.live.LiveCourseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 直播课程表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
@RestController
@RequestMapping("/service_live/liveCourse")
public class LiveCourseController {
    private LiveCourseService liveCourseService;

    @Autowired
    public void setLiveCourseService(LiveCourseService liveCourseService) {
        this.liveCourseService = liveCourseService;
    }

    @ApiOperation(value = "分页查询直播列表")
    @GetMapping("pageQueryLiveInfo/{index}/{limit}")
    public Result pageQueryLiveInfo(
            @PathVariable Long index,
            @PathVariable Long limit) {
        Page<LiveCourse> livePage = new Page<>(index, limit);
        IPage<LiveCourse> pageModel = liveCourseService.pageQueryLiveInfo(livePage);
        return Result.ok().data("lives", pageModel.getRecords())
                .data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "添加直播课程")
    @PostMapping("addLiveCourse")
    public Result addLiveCourse(
            @ApiParam(name = "liveCourseFormVO", value = "要添加的直播数据", required = true)
            @RequestBody LiveCourseFormVO liveCourseFormVO) {
        liveCourseService.addLiveCourse(liveCourseFormVO);
        return Result.ok();
    }

    @ApiOperation(value = "删除直播课程")
    @DeleteMapping("deleteLiveCourse/{id}")
    public Result deleteLiveCourse(
            @ApiParam(name = "id", value = "要删除的直播课程", required = true)
            @PathVariable Long id) {
        liveCourseService.deleteLiveCourse(id);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除直播课程")
    @DeleteMapping("batchDeleteLiveCourse")
    public Result batchDeleteLiveCourse(
            @ApiParam(name = "ids", value = "要删除的直播列表", required = true)
            @RequestBody List<Long> ids) {
        liveCourseService.batchDeleteLiveCourse(ids);
        return Result.ok();
    }

    @ApiOperation(value = "更新直播课程信息")
    @PostMapping("updateLiveCourse")
    public Result updateLiveCourse(
            @ApiParam(name = "liveCourseFormVO", value = "更新信息", required = true)
            @RequestBody LiveCourseFormVO liveCourseFormVO) {
        liveCourseService.updateLiveCourse(liveCourseFormVO);
        return Result.ok();
    }

    @ApiOperation(value = "获取直播课程信息")
    @GetMapping("getLiveCourse/{id}")
    public Result getLiveCourse(@PathVariable Long id) {
        LiveCourse liveCourse = liveCourseService.getById(id);
        return Result.ok().data("liveCourse", liveCourse);
    }

    @ApiOperation(value = "获取直播课程信息和描述信息")
    @GetMapping("getLiveCourseVO/{id}")
    public Result getLiveCourseVO(@PathVariable Long id) {
        LiveCourseFormVO liveCourseFormVO = liveCourseService.getLiveCourseVO(id);
        return Result.ok().data("liveCourseFormVO", liveCourseFormVO);
    }

    @ApiOperation(value = "获取最近的直播")
    @GetMapping("getLatelyLiveList")
    public Result getLatelyLiveList() {
        List<LiveCourseVO> list = liveCourseService.getLatelyLiveList();
        return Result.ok().data("list", list);
    }

    @ApiOperation(value = "获取直播账号信息")
    @GetMapping("getLiveCourseAccount/{id}")
    public Result getLiveCourseAccount(
            @ApiParam(name = "id", value = "要获取的直播id", required = true)
            @PathVariable Long id) {
        LiveCourseAccount liveCourseAccount = liveCourseService.getLiveCourseAccountByCourseId(id);
        return Result.ok().data("liveCourseAccount", liveCourseAccount);
    }

    @ApiOperation(value = "获取直播配置信息")
    @GetMapping("getLiveCourseConfig/{id}")
    public Result getLiveCourseConfig(
            @ApiParam(name = "id", value = "要获取的直播id", required = true)
            @PathVariable Long id) {
        LiveCourseConfigVO liveCourseConfigVO = liveCourseService.getLiveCourseConfig(id);
        return Result.ok().data("liveCourseConfigVO", liveCourseConfigVO);
    }

    @ApiOperation(value = "修改配置")
    @PutMapping("updateLiveConfig")
    public Result updateLiveConfig(
            @ApiParam(name = "liveCourseConfigVO", value = "要修改的配置数据", required = true)
            @RequestBody LiveCourseConfigVO liveCourseConfigVO) {
        liveCourseService.updateLiveConfig(liveCourseConfigVO);
        return Result.ok();
    }

    @ApiOperation(value = "获取用户access_token")
    @GetMapping("getAccessToken/{id}")
    public Result getAccessToken(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable Long id) {
        JSONObject object = liveCourseService.getAccessToken(id, AuthContextHolder.getUserId());
        return Result.ok().data("object", object);
    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("userGetLiveCourseInfo/{courseId}")
    public Result userGetLiveCourseInfo(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long courseId) {
        Map<String, Object> map = liveCourseService.userGetLiveCourseInfo(courseId);
        return Result.ok().data("map", map);
    }
}

