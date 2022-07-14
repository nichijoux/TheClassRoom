package com.zh.tcr.user.controller;

import com.alibaba.fastjson.JSON;
import com.zh.tcr.common.utils.JwtHelper;
import com.zh.tcr.model.entity.user.UserInfo;
import com.zh.tcr.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

@Api(tags = "微信登录认证")
@Controller
@RequestMapping("/service_user/wechat")
public class WechatController {
    private UserInfoService userInfoService;

    private WxMpService wxMpService;

    @Value("${tencent.wechat.callback_url}")
    private String userInfoUrl;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    @ApiOperation(value = "微信授权跳转")
    @GetMapping("authorize")
    public String authorize(
            @RequestParam("returnUrl") String returnUrl,
            HttpServletRequest request) {
        String url = wxMpService.getOAuth2Service().buildAuthorizationUrl(
                userInfoUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO,
                URLEncoder.encode(returnUrl.replace("tcr", "#")));
        return "redirect:" + url;
    }

    @ApiOperation(value = "获取用用户信息")
    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxOAuth2Service oAuth2Service = wxMpService.getOAuth2Service();
        try {
            // 拿着code请求
            WxOAuth2AccessToken wxMpOAuth2AccessToken = oAuth2Service.getAccessToken(code);
            //获取openid
            String openId = wxMpOAuth2AccessToken.getOpenId();
            System.out.println("openid:" + openId);

            // 获取微信信息
            WxOAuth2UserInfo wxMpUser = oAuth2Service.getUserInfo(wxMpOAuth2AccessToken, null);
            System.out.println("wxMpUser:" + JSON.toJSONString(wxMpUser));

            //获取微信信息添加数据库
            UserInfo userInfo = userInfoService.getUserInfoByOpenid(openId);
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setOpenId(openId);
                userInfo.setNickName(wxMpUser.getNickname());
                userInfo.setAvatar(wxMpUser.getHeadImgUrl());
                userInfo.setSex(wxMpUser.getSex());
                userInfo.setProvince(wxMpUser.getProvince());
                userInfoService.save(userInfo);
            }

            //授权完成之后，跳转具体功能页面
            //生成token，按照一定规则生成字符串，可以包含用户信息
            String token = JwtHelper.createToken(userInfo.getId(), userInfo.getNickName());
            if (!returnUrl.contains("?")) {
                return "redirect:" + returnUrl + "?token=" + token;
            } else {
                return "redirect:" + returnUrl + "&token=" + token;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }


}
