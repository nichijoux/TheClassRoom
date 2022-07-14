package com.zh.tcr.wechat.controller;

import com.zh.tcr.common.result.Result;
import com.zh.tcr.common.utils.AuthContextHolder;
import com.zh.tcr.common.utils.Base64Util;
import com.zh.tcr.model.vo.wechat.WxJsapiSignatureVO;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service_wechat/share")
public class ShareController {
    private WxMpService wxMpService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @GetMapping("getSignature")
    public Result getSignature(@RequestParam("url") String url) throws WxErrorException {
        String currentUrl = url.replace("tcr", "#");
        WxJsapiSignature jsapiSignature = wxMpService.createJsapiSignature(currentUrl);

        WxJsapiSignatureVO wxJsapiSignatureVO = new WxJsapiSignatureVO();
        BeanUtils.copyProperties(jsapiSignature, wxJsapiSignatureVO);
        wxJsapiSignatureVO.setUserEedId(Base64Util.base64Encode(AuthContextHolder.getUserId() + ""));
        return Result.ok().data("wxJsapiSignatureVO",wxJsapiSignatureVO);
    }
}