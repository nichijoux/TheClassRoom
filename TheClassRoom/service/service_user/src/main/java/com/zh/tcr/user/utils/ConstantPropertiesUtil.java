package com.zh.tcr.user.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${tencent.wechat.app_id}")
    private String appId;

    @Value("${tencent.wechat.app_secret}")
    private String appSecret;

    public static String ACCESS_APP_ID;
    public static String ACCESS_APP_SECRET;

    @Override
    public void afterPropertiesSet() {
        ACCESS_APP_ID = appId;
        ACCESS_APP_SECRET = appSecret;
    }
}
