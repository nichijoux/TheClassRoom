package com.zh.tcr.live.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.tcr.model.entity.live.LiveCourse;
import com.zh.tcr.model.entity.live.LiveCourseAccount;
import com.zh.tcr.model.vo.live.LiveCourseConfigVO;
import com.zh.tcr.model.vo.live.LiveCourseFormVO;
import com.zh.tcr.model.vo.live.LiveCourseVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 直播课程表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseService extends IService<LiveCourse> {

    // 分页查询直播列表
    IPage<LiveCourse> pageQueryLiveInfo(Page<LiveCourse> livePage);

    // 添加直播课程
    void addLiveCourse(LiveCourseFormVO liveCourseFormVO);

    // 删除直播课程
    void deleteLiveCourse(Long id);

    // 更新直播课程信息
    void updateLiveCourse(LiveCourseFormVO liveCourseFormVO);

    // 获取直播课程信息和描述信息
    LiveCourseFormVO getLiveCourseVO(Long id);

    // 获取最近的直播
    List<LiveCourseVO> getLatelyLiveList();

    // 修改配置
    void updateLiveConfig(LiveCourseConfigVO liveCourseConfigVO);

    // 根据课程id获取直播账号信息
    LiveCourseAccount getLiveCourseAccountByCourseId(Long id);

    // 获取直播配置信息
    LiveCourseConfigVO getLiveCourseConfig(Long id);

    // 批量删除直播课程
    void batchDeleteLiveCourse(List<Long> ids);

    // 获取用户accessToken
    JSONObject getAccessToken(Long id, Long userId);

    // 用户获取直播信息
    Map<String, Object> userGetLiveCourseInfo(Long courseId);
}
