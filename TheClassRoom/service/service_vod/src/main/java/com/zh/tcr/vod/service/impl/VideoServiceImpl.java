package com.zh.tcr.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.model.entity.vod.Video;
import com.zh.tcr.vod.mapper.VideoMapper;
import com.zh.tcr.vod.service.VideoService;
import com.zh.tcr.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    private VodService vodService;

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }

    // 根据id删除视频信息
    @Override
    public void deleteVideoInfo(Long id) {
        // 获取要删除的视频信息
        Video video = baseMapper.selectById(id);
        // 获取video中的视频id
        String videoSourceId = video.getVideoSourceId();
        // 删除云端数据
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodService.deleteVideo(videoSourceId);
        }
        // 删除数据库信息
        baseMapper.deleteById(id);
    }

    // 根据章节id删除视频信息
    @Override
    public void deleteVideoByChapterId(Long chapterId) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        List<Video> videoList = baseMapper.selectList(wrapper);
        for (Video video : videoList) {
            String videoSourceId = video.getVideoSourceId();
            // 删除云端视频
            if (!StringUtils.isEmpty(videoSourceId)) {
                vodService.deleteVideo(videoSourceId);
            }
        }
        baseMapper.delete(wrapper);
    }
}
