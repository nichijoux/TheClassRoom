package com.zh.tcr.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.tcr.model.entity.vod.VideoVisitor;
import com.zh.tcr.model.vo.vod.VideoVisitorCountVO;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {
    // 根据课程id和开始时间、结束时间查询查看视频的数据
    List<VideoVisitorCountVO> viewCount(Long courseId, String startDate, String endDate);
}
