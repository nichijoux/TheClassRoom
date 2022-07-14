package com.zh.tcr.user.controller;


import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/service_user/user_info")
public class UserInfoController {
    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @ApiOperation(value = "根据id远程调用获取用户信息")
    @GetMapping("remoteGetUserInfo/{id}")
    public UserInfo remoteGetUserInfo(
            @ApiParam(name = "id", value = "要查询的用户的id", required = true)
            @PathVariable Long id) {
        return userInfoService.getById(id);
    }
}

