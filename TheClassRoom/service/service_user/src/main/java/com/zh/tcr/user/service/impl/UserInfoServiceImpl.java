package com.zh.tcr.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.user.mapper.UserInfoMapper;
import com.zh.tcr.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    // 根据openId获取用户信息
    @Override
    public UserInfo getUserInfoByOpenid(String openId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id", openId);
        return baseMapper.selectOne(wrapper);
    }
}
