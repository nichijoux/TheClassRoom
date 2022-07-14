package com.zh.tcr.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

// 腾讯云视频点播服务
public interface VodService {
    //上传视频
    String uploadVideo(MultipartFile file);

    //删除腾讯云视频
    void deleteVideo(String videoSourceId);

    //点播视频播放接口
    Map<String, Object> getPlayAuth(Long courseId, Long videoId);
}
