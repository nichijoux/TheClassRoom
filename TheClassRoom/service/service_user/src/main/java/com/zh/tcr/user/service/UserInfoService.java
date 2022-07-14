package com.zh.tcr.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.user.UserInfo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
public interface UserInfoService extends IService<UserInfo> {
    // 根据openId获取用户信息
    UserInfo getUserInfoByOpenid(String openId);
}
