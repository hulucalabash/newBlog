package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-15 15:16
        *Description:博客管理，只有登录才可以管理
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    /*跳转到博客列表页面*/
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "4") Integer pageSize,
                        Model model){

        model.addAttribute("types",typeService.getAllTypes());
        model.addAttribute("tags",tagService.getAllTags());
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(blogService.getAllBlogs());

        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("pageInfo",pageInfo);


        return "admin/blogs";
    }

    /*跳转到博客新增页面*/
    @GetMapping("/inputblog")
    public String inputblog(Model model){
        model.addAttribute("types",typeService.getAllTypes());
        model.addAttribute("tags",tagService.getAllTags());
        return "admin/blogs-input";
    }

    @PostMapping("/blog/add")
    public String addBlog(Blog blog, HttpServletRequest request){
        if (blog!=null){

            System.out.println("获取的blogId"+blog.getId());
            blogService.saveBlog(blog);
            Blog blog1 = blogService.getBlogByTitle(blog);
            System.out.println("=======================");
            System.out.println("获取的blog1Id"+blog1.getId());
           /* String tag_ids = request.getParameter("tag_ids");*/
            /*tag_ids的值*/
            System.out.println(blog.getTag_ids());
            for (Integer tagId:blog.getTag_ids()) {
                blogService.saveBlogTag(blog1.getId(),tagId);
            }


            return "redirect:/admin/blogs";

        }else {
            return "redirect:/admin/inputblog";
        }
    }

    /*去修改blog的页面*/
    @GetMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable Long id,Model model){
            model.addAttribute("types",typeService.getAllTypes());
            model.addAttribute("tags",tagService.getAllTags());
            Blog blog = blogService.getBlogById(id);
            /*将type存入blog中*/
        Integer typeId = blogService.getTypeIdByBlogId(id);
        Type type = typeService.getTypeById(typeId);
        blog.setType(type);
        model.addAttribute("blog",blog);
            return "admin/blogs-input";
    }
    /*修改blog的逻辑操作*/
    @PostMapping("/blog/update")
    public String alterblog(Blog blog){
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";
    }

    /*删除blog*/
    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id){

        blogService.deleteBlogById(id);
        return "redirect:/admin/blogs";
    }
}
