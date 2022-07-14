package com.zh.tcr.wechat.config;

import com.zh.tcr.wechat.utils.ConstantPropertiesUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatConfig {
    private ConstantPropertiesUtil constantProperties;

    @Autowired
    public void setConstantProperties(ConstantPropertiesUtil constantProperties) {
        this.constantProperties = constantProperties;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl wxMpConfigStorage = new WxMpDefaultConfigImpl();
        wxMpConfigStorage.setAppId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        wxMpConfigStorage.setSecret(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        return wxMpConfigStorage;
    }
}
