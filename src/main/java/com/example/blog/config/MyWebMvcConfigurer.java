package com.example.blog.config;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-15 16:43
        *Description:
        *
        **/

import com.example.blog.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login","/login","/");

    }
}
