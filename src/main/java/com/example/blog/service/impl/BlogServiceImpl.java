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
import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Tag;
import com.example.blog.pojo.Type;
import com.example.blog.pojo.User;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMap blogMap;
    @Autowired
    TypeMap typeMap;
    @Autowired
    TagMap tagMap;

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogMap.getAllBlogs();
        for (Blog blog:blogs) {
            System.out.println(blog.getUpdate_time());
            /*将blog中的Type属性补全*/
            Type type = typeMap.getTypeById(blog.getType_id());
            /*System.out.println(type);*/
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
}
