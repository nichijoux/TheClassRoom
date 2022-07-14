package com.zh.tcr.sms.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtil {
    private static final DecimalFormat codeFormat = new DecimalFormat("0000");

    // 获取4位的手机验证码
    public static String getFourBitRandom() {
        Random random = new Random();
        return codeFormat.format(random.nextInt(10000));
    }
}