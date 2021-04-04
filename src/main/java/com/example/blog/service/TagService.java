package com.example.blog.service;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 16:47
        *Description:分类管理
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Tag;
import com.example.blog.pojo.TagTop;

import java.util.List;

public interface TagService {

    /*增加分类*/
    void saveTag(Tag tag);

    /*删除分类*/
    void deleteTag(Integer id);

    /*修改分类*/
    void updateTag(Tag tag);

    /*查询分类*/
    List<Tag> getAllTags();
    Tag getTagById(Integer id);
    Tag getTagByName(String name);
    List<TagTop> getTagTop(Integer num);
    List<Tag> getBlogTag(Blog blog);

}
