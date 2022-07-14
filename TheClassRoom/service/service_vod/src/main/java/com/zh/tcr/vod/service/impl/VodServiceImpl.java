package com.zh.tcr.vod.service.impl;

import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.model.entity.vod.Video;
import com.zh.tcr.vod.service.VideoService;
import com.zh.tcr.vod.service.VodService;
import com.zh.tcr.vod.utils.ConstantPropertiesUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VodServiceImpl implements VodService {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @Value("${tencent.video.appid}")
    private String appId;

    // 上传视频
    @Override
    public String uploadVideo(MultipartFile file) {
        //指定当前腾讯云账号id和key
        VodUploadClient client = new VodUploadClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //上传请求对象
        VodUploadRequest request = new VodUploadRequest();
        try {
            //设置视频文件在本地路径
            String originalName = file.getOriginalFilename();
            String ext = "." + FilenameUtils.getExtension(originalName);
            String fileName = UUID.randomUUID() + ext;
            File targetFile = new File("D:/fuckTencent/", fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
            request.setMediaFilePath(targetFile.getAbsolutePath());
            //任务流
            request.setProcedure("LongVideoPreset");
            //调用方法上传视频，指定地域
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            //获取上传之后视频id
            String fileId = response.getFileId();
            //noinspection ResultOfMethodCallIgnored
            targetFile.delete();
            return fileId;
        } catch (Exception e) {
            // 业务方进行异常处理
            throw new TCRException(20001, "上传视频失败");
        }
    }

    // 删除腾讯云视频
    @Override
    public void deleteVideo(String videoSourceId) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey
            Credential cred = new Credential(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vod.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            VodClient client = new VodClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(videoSourceId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = client.DeleteMedia(req);
            // 输出json格式的字符串回包
            System.out.println(DeleteMediaResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            throw new TCRException(20001, "删除视频失败");
        }
    }

    // 点播视频播放接口
    @Override
    public Map<String, Object> getPlayAuth(Long courseId, Long videoId) {
        //根据小节id获取小节对象，获取腾讯云视频id
        Video video = videoService.getById(videoId);
        if (video == null) {
            throw new TCRException(20001, "小节信息不存在");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("videoSourceId", video.getVideoSourceId());
        map.put("appId", appId);
        return map;
    }
}
