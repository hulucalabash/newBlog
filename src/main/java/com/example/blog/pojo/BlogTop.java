package com.example.blog.pojo;

import org.springframework.stereotype.Component;

/*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-30 16:36
        *Description:首页博客推荐右边列表
        *
        **/
@Component
public class BlogTop {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "BlogTop{" +
                "title='" + title + '\'' +
                '}';
    }
}
