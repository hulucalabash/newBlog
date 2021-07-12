package com.example.blog.service.impl;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-20 10:59
        *Description:博客管理
        *
        **/

import com.example.blog.mapper.BlogMap;
import com.example.blog.mapper.TagMap;
import com.example.blog.mapper.TypeMap;
import com.example.blog.mapper.UserMap;
import com.example.blog.pojo.*;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMap blogMap;
    @Autowired
    TypeMap typeMap;
    @Autowired
    TagMap tagMap;
    @Autowired
    UserMap userMap;

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogMap.getAllBlogs();
        for (Blog blog:blogs) {
            System.out.println(blog.getUpdate_time());
            /*将blog中的Type属性补全*/
            Type type = typeMap.getTypeById(blog.getType_id());
            User user = userMap.getUserById(blog.getUser_id());
            System.out.println(user);
            /*System.out.println(type);*/
            blog.setUser(user);
            blog.setType(type);
        }

        return blogs;
    }

    /*新增blog*/
    @Override
    public void saveBlog(Blog blog) {
            if(blog.getViews()==null){
                blog.setViews(0);
            }
            if (blog.getShare_statement()==null){
               /* System.out.println(blog.getTitle());
                System.out.println(blog.getShare_statement());*/
                blog.setShare_statement(0);
                /*System.out.println(blog.getShare_statement());*/
            }

            if (blog.getAppreciation()==null){
                blog.setAppreciation(0);
            }

            if (blog.getCommentable()==null){
                blog.setCommentable(0);
            }

            if (blog.getRecommend()==null){
                blog.setRecommend(0);
            }

            /*后端补全创建时间更新时间*/
        Date d1 = new Date();
        java.sql.Date date = new java.sql.Date(d1.getTime());
        System.out.println(date);
        blog.setCreate_time(date);
        blog.setUpdate_time(date);

        blogMap.saveBlog(blog);



    }

    @Override
    public void saveBlogTag(Long blogId,Integer tagId) {
        blogMap.saveBlogTag(blogId,tagId);
    }


    /*根据id获取blog*/
    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogMap.getBlogById(id);
        return blog;
    }

    @Override
    public Blog getBlogByTitle(Blog blog) {
        Blog blog1 = blogMap.getBlogByTitle(blog.getTitle());
        return blog1;

    }

    /*获得分类页面上的blog*/
    @Override
    public List<Blog> getTypeBlog(Integer id) {
        List<Blog> typeBlogs = blogMap.getTypeBlogByBlogId(id);
        return typeBlogs;
    }

    /*获得分类页面上的blog*/
    @Override
    public List<Blog> getTagBlog(Integer id) {
        List<Blog> tagBlogs = new ArrayList<>();
        List<Long> blogIds = blogMap.getBlogIdByTagId(id);
        for (Long blogId:blogIds
             ) {
            Blog blog = blogMap.getBlogById(blogId);
            tagBlogs.add(blog);

        }

        return tagBlogs;
    }

    /*更新blog*/
    @Override
    public void updateBlog(Blog blog) {
        Date d1 = new Date();
        java.sql.Date date = new java.sql.Date(d1.getTime());
        System.out.println(date);
            blog.setUpdate_time(date);
        if(blog.getViews()==null){
            blog.setViews(0);
        }
        if (blog.getShare_statement()==null){
               /* System.out.println(blog.getTitle());
                System.out.println(blog.getShare_statement());*/
            blog.setShare_statement(0);
            /*System.out.println(blog.getShare_statement());*/
        }else {
            blog.setShare_statement(1);
        }


        if (blog.getAppreciation()==null){
            blog.setAppreciation(0);
        }else {
            blog.setAppreciation(1);
        }

        if (blog.getCommentable()==null){
            blog.setCommentable(0);
        }else {
            blog.setCommentable(1);
        }

        if (blog.getRecommend()==null){
            blog.setRecommend(0);
        }else {
            blog.setRecommend(1);
        }

        /*更新blog_tag表*/
        blogMap.updateBlog(blog);
        for (Integer tagId:blog.getTag_ids()
             ) {
            blogMap.updateBlogTag(blog.getId(),tagId);
        }

    }

    @Override
    public Integer getTypeIdByBlogId(Long id) {
        Integer typeId = blogMap.getTypeIdByBlogId(id);
        System.out.println(typeId);
        return typeId;
    }

    /*删除blog 与blog_tag两张表里的数据*/
    @Override
    public void deleteBlogById(Long id) {
        blogMap.deleteBlogById(id);
        blogMap.deleteBlogTagById(id);
    }

    @Override
    public void addBlogView(Blog blog) {
        blogMap.addBlogView(blog);
    }

    @Override
    public List<BlogTop> getBlogTop(Integer num) {
        List<BlogTop> blogTop = blogMap.getBlogTop(num);
        return blogTop;
    }

    /*获取归档博客*/
    @Override
    public Map<Integer,List<Blog>> archivesBlog() {
        Map<Integer,List<Blog>>map =new HashMap<>();
        List<Integer> years = blogMap.getYear();
        for (Integer year:years
             ) {
            List<Blog> blogs = blogMap.getBlogsByYear(year);
            map.put(year,blogs);
        }
        return map;


    }

  /*  @Override
    public Integer countBlog() {
   Integer countBlog = blogMap.countBlog();
        return countBlog;
    }*/

    /*注入实体类user*/
    @Override
    public void setUser(Blog blog) {
        User user = userMap.getUserById(blog.getUser_id());
        blog.setUser(user);

    }

    /*注入实体类type*/
    @Override
    public void setType(Blog blog) {
        Type type = typeMap.getTypeById(blog.getType_id());
        blog.setType(type);
    }

    /*注入实体类的列表tags*/
    @Override
    public void setTag(Blog blog) {
        List<Tag> tags = tagMap.getBlogTags(blog);
        blog.setTags(tags);
      /*  for (Tag tag:tags
             ) {
            System.out.println(tag);

        }*/
    }
}
