package com.zh.tcr.live.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.tcr.model.entity.live.LiveCourse;
import com.zh.tcr.model.vo.live.LiveCourseVO;

import java.util.List;

/**
 * <p>
 * 直播课程表 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-13
 */
public interface LiveCourseMapper extends BaseMapper<LiveCourse> {
    //获取最近的直播
    List<LiveCourseVO> getLatelyLiveList();
}
