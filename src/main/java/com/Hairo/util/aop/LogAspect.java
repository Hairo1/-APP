package com.Hairo.util.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/23 15:19
 * 作用描述:
 *  AOP切面实现日志操作记录
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = Logger.getLogger(LogAspect.class);
    @Pointcut("execution(public * com.Hairo.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 使用AOP前置通知拦截请求参数信息
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求URL : " + java.net.URLDecoder.decode(request.getRequestURL().toString(),"utf-8") );
        logger.info("请求类型 : " + request.getMethod());
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
        if(ret == null){
            logger.info("返回内容 : " + "null");
            return;
        }
        // 处理完请求，返回内容
        if(ret.toString().length()>200){
            logger.info("返回内容 : " + ret.toString().substring(0,200)+"........(内容过多，只显示200个字符)");
        }else{
            logger.info("返回内容 : " + ret);
        }
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void throwing(JoinPoint joinPoint, Exception e) {
        logger.error("抛出的异常是:" + e.getClass());
    }
}
