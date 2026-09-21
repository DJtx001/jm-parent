package com.djh.exception;

import com.djh.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;

@Slf4j
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result GlobalExceptionHandler(Exception e) {
        log.error("全局异常处理器发现错误",e);
        return Result.error("服务器异常");
    }
    @ExceptionHandler
    public Result LoginExceptionHandler(LonginExcepention e) {
        log.error("登录异常处理器发现错误",e);
        String message = e.getMessage();
        return Result.error(message);
    }
    //处理业务异常 BusinessException
    @ExceptionHandler
    public Result ex(BusinessException e){
        log.error("业务异常", e);
        return Result.error(e.getMessage());
    }

}
