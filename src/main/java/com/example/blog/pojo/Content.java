package com.example.blog.pojo;
        /*
        *Copyright:hulu有限gh big company
        *Date:2021-05-16 14:07
        *Description:京东获取
        *
        **/

import org.springframework.stereotype.Component;

@Component
public class Content {
    private String img;
    private String price;
    private String title;

    @Override
    public String toString() {
        return "Content{" +
                "img='" + img + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
