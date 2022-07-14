package com.zh.tcr.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.tcr.model.entity.vod.Chapter;
import com.zh.tcr.model.entity.vod.Video;
import com.zh.tcr.model.vo.vod.ChapterVO;
import com.zh.tcr.model.vo.vod.VideoVO;
import com.zh.tcr.vod.mapper.ChapterMapper;
import com.zh.tcr.vod.service.ChapterService;
import com.zh.tcr.vod.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-11
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    // 获取章节和小节
    @Override
    public List<ChapterVO> getChapterAndVideoInfo(Long courseId) {
        // 定义最终数据list集合
        List<ChapterVO> finalChapterList = new ArrayList<>();

        // 根据courseId获取课程里面所有章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);

        // 根据courseId获取课程里面所有小节
        LambdaQueryWrapper<Video> wrapperVideo = new LambdaQueryWrapper<>();
        wrapperVideo.eq(Video::getCourseId, courseId);
        List<Video> videoList = videoService.list(wrapperVideo);

        // 封装章节
        // 遍历所有章节
        for (Chapter chapter : chapterList) {
            // 得到课程每个章节
            // chapter -- ChapterVo
            ChapterVO chapterVo = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVo);
            //得到每个章节对象放到finalChapterList集合
            finalChapterList.add(chapterVo);

            // 封装章节里面小节
            // 创建list集合用户封装章节所有小节
            List<VideoVO> videoVoList = new ArrayList<>();
            // 遍历小节list
            for (Video video : videoList) {
                // 判断小节是哪个章节下面
                // 章节id  和 小节chapter_id
                if (chapter.getId().equals(video.getChapterId())) {
                    // video  -- VideoVo
                    VideoVO videoVo = new VideoVO();
                    BeanUtils.copyProperties(video, videoVo);
                    //放到videoVoList
                    videoVoList.add(videoVo);
                }
            }
            // 把章节里面所有小节集合放到每个章节里面
            chapterVo.setChildren(videoVoList);
        }
        return finalChapterList;
    }

    // 根据章节id删除章节,同时删除里面对应的小节视频
    @Override
    public void deleteChapter(Long id) {
        // 删除小节
        videoService.deleteVideoByChapterId(id);
        // 删除章节
        baseMapper.deleteById(id);
    }

    // 根据课程id删除章节信息,同时也会删除小节
    @Override
    public void deleteChapterByCourseId(Long courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(wrapper);
        // 删除章节以及小节
        for (Chapter chapter : chapters) {
            deleteChapter(chapter.getId());
        }
    }
}