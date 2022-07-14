package com.zh.tcr.vod.controller;

import com.zh.tcr.common.exception.TCRException;
import com.zh.tcr.common.result.Result;
import com.zh.tcr.vod.service.VodService;
import com.zh.tcr.vod.utils.ConstantPropertiesUtil;
import com.zh.tcr.vod.utils.Signature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Random;

@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/service_vod/vod")
public class VodController {
    private VodService vodService;

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }

    //返回客户端上传视频签名
    @ApiOperation(value = "客户端获取上传视频签名")
    @GetMapping("getSignature")
    public Result getSignature() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24); // 签名有效期：1天
        try {
            String signature = sign.getUploadSignature();
            return Result.ok().data("signature", signature);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TCRException(20001, "获取签名失败");
        }
    }

    @ApiOperation(value = "上传视频到腾讯云")
    @PostMapping("uploadVideo")
    public Result uploadVideo(
            @RequestBody MultipartFile file) {
        String fileId = vodService.uploadVideo(file);
        return Result.ok().data("fileId", fileId);
    }

    @ApiOperation(value = "删除腾讯云云端视频")
    @DeleteMapping("deleteVideo/{fileId}")
    public Result deleteVideo(@PathVariable String fileId) {
        vodService.deleteVideo(fileId);
        return Result.ok();
    }

    @ApiOperation(value = "根据课程id、视频id获取视频凭证")
    @GetMapping("getPlayAuth/{courseId}/{videoId}")
    public Result getPlayAuth(
            @ApiParam(value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(value = "视频id", required = true)
            @PathVariable Long videoId) {
        Map<String, Object> playAuth = vodService.getPlayAuth(courseId, videoId);
        return Result.ok().data("playAuth", playAuth);
    }
}
