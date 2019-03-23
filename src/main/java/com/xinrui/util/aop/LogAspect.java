package com.xinrui.util.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 项目名称： xinrui
 * 作 者   ： Hairo
 * 创建时间: 2019/3/23 15:19
 * 作用描述:
 *  AOP切面实现日志操作记录
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = Logger.getLogger(LogAspect.class);
    @Pointcut("execution(public * com.xinrui.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 使用AOP前置通知拦截请求参数信息
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            logger.info("name:"+ name+"\tvalue:"+ request.getParameter(name));
        }
    }
    /**
     * 后置通知
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("返回内容 : " + ret);
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void throwing(JoinPoint joinPoint, Exception e) {
        logger.info("抛出的异常是:" + e.getClass());
    }
}
