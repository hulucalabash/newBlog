package com.example.blog.mapper;

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.BlogTop;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-20 11:06
        *Description:博客表
        *
        **/
@Repository
@Mapper
public interface BlogMap {

    @Select("Select * from blog")
    List<Blog> getAllBlogs();

    @Insert("Insert into blog (id,title, content, first_picture, flag,views, appreciation, share_statement, commentable,published," +
            "recommend, create_time, update_time, type_id,user_id, description)\n" +
            "        values (#{id},#{title},#{content},#{first_picture},#{flag},#{views},#{appreciation},\n" +
            "        #{share_statement},#{commentable},#{published},#{recommend},#{create_time},\n" +
            "        #{update_time},#{type_id},#{user_id},#{description})")
    void saveBlog(Blog blog);

    @Insert("Insert into blogtag (blog_id,tag_id) values(#{blogId},#{tagId})")
    void saveBlogTag(Long blogId,Integer tagId);

    @Select("Select * from blog where title=#{title}")
    Blog getBlogByTitle(String title);

    @Select("Select * from blog where id=#{id}")
    Blog getBlogById(Long id);

    @Update("Update blog set title=#{title},content=#{content},first_picture=#{first_picture}," +
            "flag=#{flag},appreciation=#{appreciation},share_statement=#{share_statement}," +
            "commentable=#{commentable},recommend=#{recommend},update_time=#{update_time}," +
            "type_id=#{type_id},description=#{description} where id=#{id}" )
    void updateBlog(Blog blog);

    @Update("Update blogtag set tag_id=#{tagId} where blog_id=#{blogId}")
    void updateBlogTag(Long blogId,Integer tagId);

    @Select("Select type_id from blog where id=#{id}")
    Integer getTypeIdByBlogId(Long id);

    @Delete("Delete from blog where id=#{id}")
    void deleteBlogById(Long id);

    @Delete("Delete from blogtag where blog_id=#{id}")
    void deleteBlogTagById(Long id);

    @Select("Select title from blog order by update_time limit #{num}")
    List<BlogTop> getBlogTop(Integer num);

    @Update("Update blog set views=#{views} where id=#{id}")
    void addBlogView(Blog blog);
}
