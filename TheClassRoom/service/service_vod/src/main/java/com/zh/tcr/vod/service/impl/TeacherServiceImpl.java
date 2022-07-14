package com.zh.tcr.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.model.entity.vod.Teacher;
import com.zh.tcr.model.vo.vod.TeacherQueryCondition;
import com.zh.tcr.vod.mapper.TeacherMapper;
import com.zh.tcr.vod.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-10
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    // 条件分页查询讲师信息
    @Override
    public void pageQueryTeacherInfo(Page<Teacher> teacherPage, TeacherQueryCondition queryCondition) {
        if (queryCondition == null) {
            baseMapper.selectPage(teacherPage, null);
            return;
        }
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        // 获取查询条件
        String name = queryCondition.getName();
        Integer level = queryCondition.getLevel();
        String joinDateBegin = queryCondition.getJoinDateBegin();
        String joinDateEnd = queryCondition.getJoinDateEnd();
        // 设定查询条件
        if (!StringUtils.isEmpty(name)) wrapper.like("name", name);
        if (!StringUtils.isEmpty(level)) wrapper.eq("level", level);
        if (!StringUtils.isEmpty(joinDateBegin)) wrapper.ge("join_date", joinDateBegin);
        if (!StringUtils.isEmpty(joinDateEnd)) wrapper.le("join_date", joinDateEnd);

        baseMapper.selectPage(teacherPage, wrapper);
    }
}
