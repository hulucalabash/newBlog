package com.example.blog.service.impl;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 17:02
        *Description:分类管理
        *
        **/

import com.example.blog.mapper.TagMap;
import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Tag;
import com.example.blog.pojo.TagTop;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    public TagMap tagMap;
    /*增加tag*/
    @Override
    public void saveTag(Tag tag) {
        tagMap.saveTag(tag);
    }
    /*删除分类*/
    @Override
    public void deleteTag(Integer id) {
        tagMap.deleteTag(id);
    }

    /*修改分类*/
    @Override
    public void updateTag(Tag tag) {
        tagMap.updateTag(tag);
    }

    /*获取所有分类*/
    @Override
    public List<Tag> getAllTags() {
        List<Tag> tags = tagMap.getAllTags();
        return tags;
    }
    /*根据id获取tag*/
    @Override
    public Tag getTagById(Integer id) {
        Tag tag = tagMap.getTagById(id);
        return tag;
    }
    /*根据name获取tag*/
    @Override
    public Tag getTagByName(String name) {
        return null;
    }

    @Override
    public List<TagTop> getTagTop(Integer num) {
        List<TagTop> tagTop = tagMap.getTagTop(num);
        return tagTop;
    }

    @Override
    public List<Tag> getBlogTag(Blog blog) {
        List<Tag> tags = tagMap.getBlogTags(blog);
        return tags;
    }
}
