package com.zh.tcr.vod.controller;

import com.zh.tcr.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录控制器")
@RestController
@RequestMapping("/service_vod/user")
public class LoginController {
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("name", "Super Admin")
                .data("introduction", "I am a super administrator")
                .data("roles", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @ApiOperation(value = "退出")
    @PostMapping("logout")
    public Result logout() {
        return Result.ok();
    }
}
