package com.zh.tcr.sms.service;

public interface SmsService {
    // 发送验证码
    boolean sendVerificationCode(String phone, String[] params);
}
