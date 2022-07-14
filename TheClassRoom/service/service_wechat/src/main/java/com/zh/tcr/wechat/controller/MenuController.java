package com.zh.tcr.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.DataValidation;
import com.zh.tcr.model.entity.wechat.Menu;
import com.zh.tcr.model.vo.wechat.MenuVO;
import com.zh.tcr.wechat.service.MenuService;
import com.zh.tcr.wechat.utils.ConstantPropertiesUtil;
import com.zh.tcr.wechat.utils.HttpClientUtils;
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
 * 订单明细 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Api(tags = "订单明细管理")
@RestController
@RequestMapping("/service_wechat/menu")
public class MenuController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "获取所有的菜单,并将一二级菜单封装")
    @GetMapping("getAllMenuInfo")
    public Result getAllMenuInfo() {
        List<MenuVO> menus = menuService.getAllMenuInfo();
        return Result.ok().data("menus", menus);
    }

    @ApiOperation(value = "获取所有一级菜单")
    @GetMapping("getAllOneMenuInfo")
    public Result getAllOneMenuInfo() {
        List<Menu> oneMenu = menuService.getOneMenuInfo();
        return Result.ok().data("oneMenu", oneMenu);
    }

    @ApiOperation(value = "获取access_token")
    @GetMapping("getAccessToken")
    public Result getAccessToken() {
        //拼接请求地址
        String urlPath = "https://api.weixin.qq.com/cgi-bin/token" +
                "?grant_type=client_credential" +
                "&appid=%s" +
                "&secret=%s";
        //设置路径参数
        String url = String.format(urlPath,
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //get请求
        try {
            String tokenString = HttpClientUtils.get(url);
            //获取access_token
            JSONObject jsonObject = JSONObject.parseObject(tokenString);
            String access_token = jsonObject.getString("access_token");
            return Result.ok().data("access_token", access_token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TCRException(20001, "获取access_token失败");
        }
    }

    @ApiOperation(value = "使用access_token同步微信公众号的菜单")
    @GetMapping("syncWechatMenu")
    public Result syncWechatMenu() {
        menuService.syncWechatMenu();
        return Result.ok();
    }

    @ApiOperation(value = "删除微信公众号的菜单")
    @DeleteMapping("deleteWechatMenu")
    public Result deleteWechatMenu() {
        menuService.deleteWechatMenu();
        return Result.ok();
    }

    @ApiOperation(value = "根据id获取某个菜单")
    @GetMapping("getMenuInfo/{id}")
    public Result getMenuInfo(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return Result.ok().data("menu", menu);
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("addMenuInfo")
    public Result addMenuInfo(
            @ApiParam(name = "menu", value = "要添加的菜单信息", required = true)
            @Valid @RequestBody Menu menu,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        menuService.save(menu);
        return Result.ok();
    }

    @ApiOperation(value = "更新菜单信息")
    @PostMapping("updateMenuInfo")
    public Result updateMenuInfo(
            @ApiParam(name = "menu", value = "要添加的菜单信息", required = true)
            @Valid @RequestBody Menu menu,
            @ApiParam(name = "result", value = "数据校验出错信息")
                    BindingResult result) {
        Result tempResult = DataValidation.returnIfDataIsInvalid(result);
        if (!tempResult.getSuccess()) {
            return tempResult;
        }
        menuService.updateById(menu);
        return Result.ok();
    }

    @ApiOperation(value = "根据id删除菜单")
    @DeleteMapping("deleteMenuInfo/{id}")
    public Result deleteMenuInfo(
            @ApiParam(name = "id", value = "要删除的菜单id", required = true)
            @PathVariable Long id) {
        menuService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表批量删除菜单")
    @DeleteMapping("batchDeleteMenuInfo")
    public Result batchDeleteMenuInfo(
            @ApiParam(name = "ids", value = "要删除的id列表", required = true)
            @RequestBody List<Long> ids) {
        menuService.removeByIds(ids);
        return Result.ok();
    }
}

