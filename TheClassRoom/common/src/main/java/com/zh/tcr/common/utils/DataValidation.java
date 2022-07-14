package com.zh.tcr.common.utils;

import com.zh.tcr.common.result.Result;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class DataValidation {
    public static Result returnIfDataIsInvalid(BindingResult result) {
        if (result.hasErrors()) {
            Result returnResult = Result.error();
            for (ObjectError error : result.getAllErrors()) {
                returnResult.message(error.getDefaultMessage());
            }
            return returnResult;
        }
        return Result.ok();
    }
}
