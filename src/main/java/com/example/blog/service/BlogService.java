package com.example.blog.service;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-20 10:54
        *Description:博客管理接口
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.BlogTop;

import java.util.List;

public interface BlogService {
    /*获得所有blogs*/
    List<Blog> getAllBlogs();

    /*增加blog*/
    void saveBlog(Blog blog);
    /*添加blog与tag的数据*/
    void saveBlogTag(Long blogId,Integer tagId);

    /*根据id获取blog*/
    Blog getBlogById(Long id);
    /*根据title获取Blog*/
    Blog getBlogByTitle(Blog blog);

    /*修改blog*/
    void updateBlog(Blog blog);

    /*根据博客id获取分类id*/
    Integer getTypeIdByBlogId(Long id);

    /*根据id删除blog*/
    void deleteBlogById(Long id);
    /*访问一次路由浏览次数加一*/
    void  addBlogView(Blog blog);

    /*获取首页右边的博客推荐列表*/
    List<BlogTop> getBlogTop(Integer num);

    void setUser(Blog blog);
    void setType(Blog blog);
    void setTag(Blog blog);

}
