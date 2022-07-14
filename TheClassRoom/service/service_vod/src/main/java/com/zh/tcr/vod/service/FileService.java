package com.zh.tcr.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // 上传头像
    String uploadAvatar(MultipartFile file);
}
