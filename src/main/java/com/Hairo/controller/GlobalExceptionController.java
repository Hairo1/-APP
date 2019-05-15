package com.Hairo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/10 22:30
 * 作用描述:
 * 全局异常处理类
 */
@Api(value = "全局异常处理Controller")
@ControllerAdvice
@RestController
public class GlobalExceptionController {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionController.class);

    @ApiOperation(value = "处理所有异常包含404等异常处理")
    @ExceptionHandler(value = Exception.class)
    public Object allException(HttpServletRequest req, HttpServletResponse res, Exception e) throws UnsupportedEncodingException {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.error("\n\n ");
        LOGGER.error("\n打印抛出的异常信息：");
        LOGGER.error("\t异常请求URL : " + java.net.URLDecoder.decode(request.getRequestURL().toString(), "utf-8"));
        LOGGER.error("\t异常请求方法类型 : " + request.getMethod());
        //LOGGER.error("\t异常IP : " + request.getRemoteAddr());
        LOGGER.error("\t异常类 : " + e.getClass());
        LOGGER.error("\t异常信息 : " + e.getMessage()+"\n\n");
        String code = "";
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            code = "404";
        } else {
            code = "500";
        }
        return code;
    }
}
