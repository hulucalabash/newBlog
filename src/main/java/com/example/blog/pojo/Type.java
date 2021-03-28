package com.example.blog.pojo;

import org.springframework.stereotype.Component;

/*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 16:51
        *Description:分类类
        *
        **/
@Component
public class Type {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
