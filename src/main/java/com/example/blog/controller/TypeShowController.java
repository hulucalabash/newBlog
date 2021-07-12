package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-17 11:40
        *Description:分类总页面控制器
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Type;
import com.example.blog.pojo.TypeTop;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
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
public class TypeShowController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "4") Integer pageSize,
            @PathVariable Integer id, Model model){
        /*传过的id参数是分类页面选中的分类*/
        List<Type> types = typeService.getAllTypes();
        //实际获取所有分类及其数量
        List<TypeTop> allTypes = typeService.getTypeTop(3);
        if (id==-1){
            id=types.get(0).getId();
        }
        model.addAttribute("ID",id);
        //获取所有分类
        model.addAttribute("alltypes",allTypes);

        List<Blog> typeBlogs = blogService.getTypeBlog(id);
        for (Blog typeBlog:typeBlogs
             ) {
            blogService.setUser(typeBlog);
            blogService.setType(typeBlog);
            blogService.setTag(typeBlog);

        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo=new PageInfo(typeBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "types";
    }
}
