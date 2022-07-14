package com.zh.tcr.common.client;


import com.zh.tcr.model.entity.user.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

    @ApiOperation(value = "远程调用,根据id获取用户信息")
    @GetMapping("/service_user/user_info/remoteGetUserInfo/{id}")
    UserInfo remoteGetUserInfo(@PathVariable("id") Long id);
}

