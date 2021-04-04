package com.example.blog.controller;
       /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-09 21:12
        *Description:
        *
        **/

import com.example.blog.pojo.*;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.TypeService;
import com.example.blog.service.UserService;
import com.example.blog.utils.markdown2html;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    UserService userService;
    @Autowired
    markdown2html markdown2html;
    /*----去首页---*/
    @GetMapping("/")
    public String index(@RequestParam(defaultValue ="1" ) Integer pageNum,
                        @RequestParam(defaultValue = "5") Integer pageSize,
                        Model model){
        /*由分页插件PageHelper获得*/
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(blogService.getAllBlogs());
        model.addAttribute("pageInfo",pageInfo);

        /*获得首页推荐的type*/
        List<TypeTop> typeTop = typeService.getTypeTop(3);
        /*System.out.println(typeTop);*/
        model.addAttribute("typeTop",typeTop);

        /*获得首页推荐的tag*/
        List<TagTop> tagTop = tagService.getTagTop(3);
        model.addAttribute("tagTop",tagTop);

        /*获得右侧推荐的博客 目前根据更新时间获得由更新时间推荐的blog*/
        List<BlogTop> blogTop = blogService.getBlogTop(4);
        model.addAttribute("blogTop",blogTop);
        return"index";
    }
    /*----去详情页-----*/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog",blog);
        blog.setViews(blog.getViews()+1);
        /*markdown2html*/
        String html = markdown2html.convert(blog.getContent());
        blog.setContent(html);
        /*增加浏览次数*/
        blogService.addBlogView(blog);
        /*blog设置user*/
        blogService.setUser(blog);
        blogService.setType(blog);
        blogService.setTag(blog);
        return "blog";
    }



}
