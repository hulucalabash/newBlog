package com.example.blog.interceptor;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-15 16:34
        *Description:拦截器的类，判断是否登录
        *
        **/

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user==null||user.equals("")){
            request.setAttribute("message","没有权限，请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
