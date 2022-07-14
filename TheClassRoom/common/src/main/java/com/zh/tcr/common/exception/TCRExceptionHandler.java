package com.zh.tcr.common.exception;

import com.zh.tcr.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class TCRExceptionHandler {
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行全局异常处理");
    }

    //自定义异常处理TCRException
    @ExceptionHandler(TCRException.class)
    @ResponseBody
    public Result error(TCRException e) {
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMessage());
    }
}
