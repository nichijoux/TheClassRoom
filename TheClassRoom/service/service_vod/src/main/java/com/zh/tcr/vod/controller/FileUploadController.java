package com.zh.tcr.vod.controller;

import com.zh.tcr.common.result.Result;
import com.zh.tcr.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Api(tags = "头像上传管理")
@RestController
@RequestMapping("service_vod/file")
public class FileUploadController {
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("上传头像")
    @PostMapping("uploadAvatar")
    public Result uploadAvatar(MultipartFile file) {
        // 原始文件名称
        String fileName = file.getOriginalFilename();

        // 临时文件
        if (fileName == null) return Result.error().message("文件错误");
        File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);

        // 先把文件序列化到临时目录
        try {
            file.transferTo(tempFile);
            // 尝试IO文件，判断文件的合法性
            BufferedImage bufferedImage = ImageIO.read(tempFile);
            bufferedImage.getWidth();
            bufferedImage.getHeight();
        } catch (Exception e) {
            // IO异常，不是合法的图片文件，返回异常信息
            return Result.error().message("文件不是图片文件");
        }

        String url = fileService.uploadAvatar(file);
        return Result.ok().data("url", url).message("上传文件成功");
    }
}