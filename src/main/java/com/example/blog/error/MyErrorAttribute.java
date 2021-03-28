package com.example.blog.error;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-10 11:09
        *Description:自定义的异常
        *
        **/

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("myerror","这是我自定义的异常！");
        if ((Integer)map.get("status") == 500) {
            //这里根据自己需求设置
            map.put("message", "服务器内部错误!");
        }
        if ((Integer)map.get("status") == 404) {
            map.put("message", "路径不存在!");
        }

        return map;
    }
}

