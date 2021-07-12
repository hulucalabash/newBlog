package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-17 11:40
        *Description:标签总页面控制器
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Tag;
import com.example.blog.pojo.TagTop;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
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
public class TagShowController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "4") Integer pageSize,
            @PathVariable Integer id, Model model){
        /*传过的id参数是分类页面选中的分类*/
        List<Tag> tags = tagService.getAllTags();
        //实际获取所有标签及其数量
        List<TagTop> allTags = tagService.getTagTop(3);
        if (id==-1){
            id=tags.get(0).getId();
        }
        model.addAttribute("ID",id);
        //获取所有分类
        model.addAttribute("alltags",allTags);

        List<Blog> tagBlogs = blogService.getTagBlog(id);
        for (Blog tagBlog:tagBlogs) {
            blogService.setUser(tagBlog);
            blogService.setType(tagBlog);
            blogService.setTag(tagBlog);

        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(tagBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "tags";
    }
}
