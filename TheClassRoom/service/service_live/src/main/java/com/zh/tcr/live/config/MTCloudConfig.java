package com.zh.tcr.live.config;

import com.zh.tcr.live.mtcloud.MTCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MTCloudConfig {
    private MTCloudAccountConfig mtCloudAccountConfig;

    @Autowired
    public void setMtCloudAccountConfig(MTCloudAccountConfig mtCloudAccountConfig) {
        this.mtCloudAccountConfig = mtCloudAccountConfig;
    }

    @Bean
    public MTCloud mtCloudClient() {
        return new MTCloud(mtCloudAccountConfig.getOpenId(), mtCloudAccountConfig.getOpenToken());
    }
}
