package com.zh.tcr.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.tcr.model.entity.vod.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.vo.vod.TeacherQueryCondition;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
public interface TeacherService extends IService<Teacher> {
    // 条件分页查询讲师信息
    void pageQueryTeacherInfo(Page<Teacher> teacherPage, TeacherQueryCondition queryCondition);
}
