package com.example.blog.controller;
       /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-09 21:12
        *Description:
        *
        **/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /*----去首页---*/
    @GetMapping("/")
    public String index(){
        return"index";
    }
    /*----去详情页-----*/
    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }



}
