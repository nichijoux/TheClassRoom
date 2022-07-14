package com.zh.tcr.activity.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.activity.service.CouponInfoService;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.DataValidation;
import com.zh.tcr.model.entity.activity.CouponInfo;
import com.zh.tcr.model.entity.activity.CouponUse;
import com.zh.tcr.model.vo.activity.CouponUseQueryCondition;
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
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Api(tags = "优惠券信息管理")
@RestController
@RequestMapping("/service_activity/couponInfo")
public class CouponInfoController {
    private CouponInfoService couponInfoService;

    @Autowired
    public void setCouponInfoService(CouponInfoService couponInfoService) {
        this.couponInfoService = couponInfoService;
    }

    @ApiOperation(value = "分页查询优惠卷信息")
    @GetMapping("pageQueryCouponInfo/{index}/{limit}")
    public Result pageQueryCouponInfo(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<CouponInfo> couponInfoPage = new Page<>(index, limit);
        couponInfoService.page(couponInfoPage, null);
        return Result.ok().data("total", couponInfoPage.getTotal())
                .data("couponInfos", couponInfoPage.getRecords());
    }

    @ApiOperation(value = "根据id获取优惠券信息")
    @GetMapping("getCouponInfo/{id}")
    public Result getCouponInfo(
            @ApiParam(name = "id", value = "要查询的优惠卷信息", required = true)
            @PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.ok().data("couponInfo", couponInfo);
    }

    @ApiOperation(value = "增加新的优惠卷")
    @PostMapping("addCouponInfo")
    public Result addCouponInfo(
            @ApiParam(name = "couponInfo", value = "优惠卷信息", required = true)
            @Valid @RequestBody CouponInfo couponInfo,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        couponInfoService.save(couponInfo);
        return Result.ok();
    }

    @ApiOperation(value = "修改优惠卷信息")
    @PostMapping("updateCouponInfo")
    public Result updateCouponInfo(
            @ApiParam(name = "couponInfo", value = "优惠卷信息", required = true)
            @RequestBody CouponInfo couponInfo,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        couponInfoService.updateById(couponInfo);
        return Result.ok();
    }

    @ApiOperation(value = "根据id删除优惠卷信息")
    @DeleteMapping("deleteCouponInfo/{id}")
    public Result deleteCouponInfo(
            @ApiParam(name = "id", value = "要删除的优惠卷id", required = true)
            @PathVariable String id) {
        couponInfoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表批量删除优惠卷信息")
    @DeleteMapping("batchDeleteCouponInfo")
    public Result batchDeleteCouponInfo(@RequestBody List<String> ids) {
        couponInfoService.removeByIds(ids);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询使用了的优惠卷信息")
    @PostMapping("pageQueryCouponUseInfo/{index}/{limit}")
    public Result pageQueryCouponUseInfo(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件")
            @RequestBody(required = false) CouponUseQueryCondition queryCondition) {
        Page<CouponUse> couponUsePage = new Page<>(index, limit);
        couponInfoService.pageQueryCouponInfo(couponUsePage, queryCondition);
        return Result.ok().data("total", couponUsePage.getTotal())
                .data("couponUseInfos", couponUsePage.getRecords());
    }

    @ApiOperation(value = "根据优惠券id获取优惠券信息")
    @GetMapping("remoteGetCouponInfo/{couponId}")
    public CouponInfo remoteGetCouponInfo(
            @ApiParam(name = "couponId", value = "优惠券id", required = true)
            @PathVariable Long couponId) {
        return couponInfoService.getById(couponId);
    }

    @ApiOperation(value = "根据id更新优惠券使用状态")
    @GetMapping("remoteUpdateCouponInfoUseState/{couponId}/{orderId}")
    public Boolean remoteUpdateCouponInfoUseState(
            @PathVariable Long couponId,
            @PathVariable Long orderId) {
        couponInfoService.updateCouponInfoUseState(couponId, orderId);
        return true;
    }
}

