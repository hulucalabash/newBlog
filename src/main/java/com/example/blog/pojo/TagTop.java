package com.example.blog.pojo;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-30 15:19
        *Description:首页的分类
        *
        **/

import org.springframework.stereotype.Component;

@Component
public class TagTop {
    private Integer id;
    private String name;
    private Integer num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TagTop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
