package com.example.blog.asepect;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-10 13:13
        *Description:aop日志处理
        *
        **/

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
//一个切面
@Aspect
@Component
public class LogAspect {
         private Logger log=LoggerFactory.getLogger(LogAspect.class);
         //一个切点
         @Pointcut("execution(public * com.example.blog.controller..*.*(..))")
         public void webLog(){

         }
    //方法前
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求内容
        log.info("===============请求内容===============");
        log.info("请求地址:"+request.getRequestURL().toString());
        log.info("请求方式:"+request.getMethod());
        log.info("请求类方法:"+joinPoint.getSignature());
        log.info("请求方法名字 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求类方法参数:"+ Arrays.toString(joinPoint.getArgs()));
        log.info("===============请求内容===============");

    }
    //方法返回内容
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }


}






