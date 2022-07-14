package com.zh.tcr.live.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.live.LiveCourseAccount;

/**
 * <p>
 * 直播课程账号表（受保护信息） 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseAccountService extends IService<LiveCourseAccount> {
    // 根据课程id获取直播课程账号信息
    LiveCourseAccount getLiveCourseAccountByCourseId(Long id);
}
