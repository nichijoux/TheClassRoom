package com.zh.tcr.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.model.entity.order.OrderInfo;
import com.zh.tcr.model.vo.order.OrderFormVO;
import com.zh.tcr.model.vo.order.OrderInfoQueryCondition;
import com.zh.tcr.model.vo.order.OrderInfoVO;
import com.zh.tcr.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Api(tags = "订单表管理")
@RestController
@RequestMapping("/service_order/order_info")
public class OrderInfoController {
    private OrderInfoService orderInfoService;

    @Autowired
    public void setOrderInfoService(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @ApiOperation(value = "分页查询订单信息")
    @PostMapping("pageQueryOrderInfo/{index}/{limit}")
    public Result pageQueryOrderInfo(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "订单查询条件")
            @RequestBody(required = false) OrderInfoQueryCondition queryCondition) {
        //创建page对象
        Page<OrderInfo> orderInfoPage = new Page<>(index, limit);
        orderInfoService.pageQueryOrderInfo(orderInfoPage, queryCondition);
        return Result.ok().data("total", orderInfoPage.getTotal())
                .data("pageCount", orderInfoPage.getPages())
                .data("orderInfos", orderInfoPage.getRecords());
    }

    @ApiOperation(value = "生成订单")
    @PostMapping("generateOrder")
    public Result generateOrder(
            @ApiParam(name = "orderFormVO",value = "生成订单所需数据",required = true)
            @RequestBody OrderFormVO orderFormVo) {
        Long orderId = orderInfoService.generateOrder(orderFormVo);
        return Result.ok().data("orderId",orderId);
    }

    @ApiOperation(value = "获取订单详情")
    @GetMapping("getOrderInfo/{id}")
    public Result getOrderInfo(
            @ApiParam(name = "id",value = "订单id",required = true)
            @PathVariable Long id) {
        OrderInfoVO orderInfoVo = orderInfoService.getOrderInfoById(id);
        return Result.ok().data("orderInfoVO",orderInfoVo);
    }
}

