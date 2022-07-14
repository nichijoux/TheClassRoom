package com.zh.tcr.common.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class LoginMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器，设置路径
        registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/acl/**");
        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }
}
