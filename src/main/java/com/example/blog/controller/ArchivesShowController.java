package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-18 22:16
        *Description:归档控制器
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesShowController {
    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){

        Map<Integer, List<Blog>> map = blogService.archivesBlog();
        /*Integer countBlog = blogService.countBlog();*/
        /*model.addAttribute("countBlog",countBlog);*/
        model.addAttribute("archivesMap",map);
        return "archives";
    }
}
