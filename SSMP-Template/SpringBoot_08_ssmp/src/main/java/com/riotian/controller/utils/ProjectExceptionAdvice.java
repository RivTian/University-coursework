package com.riotian.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 作为 Spring MVC 异常处理器
// @ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {

    // 拦截所有异常
    @ExceptionHandler
    public R doExcetion(Exception e) {
        // 记录日志
        // 通知运维
        // 通知开发
        e.printStackTrace();
        return new R("服务器故障，请稍后再试！");
    }

}
