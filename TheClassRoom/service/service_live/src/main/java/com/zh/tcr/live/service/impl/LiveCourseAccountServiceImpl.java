package com.zh.tcr.live.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.live.mapper.LiveCourseAccountMapper;
import com.zh.tcr.live.service.LiveCourseAccountService;
import com.zh.tcr.model.entity.live.LiveCourseAccount;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 直播课程账号表（受保护信息） 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
@Service
public class LiveCourseAccountServiceImpl extends ServiceImpl<LiveCourseAccountMapper, LiveCourseAccount> implements LiveCourseAccountService {
    // 根据课程id获取直播课程账号信息
    @Override
    public LiveCourseAccount getLiveCourseAccountByCourseId(Long id) {
        QueryWrapper<LiveCourseAccount> wrapper = new QueryWrapper<>();
        wrapper.eq("live_course_id", id);
        return baseMapper.selectOne(wrapper);
    }
}
