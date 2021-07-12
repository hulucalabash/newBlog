package com.example.blog.service;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-10 11:48
        *Description:评论业务接口
        *
        **/

import com.example.blog.pojo.Comment;

import java.util.List;

public interface CommentService {

    //查询评论列表
    List<Comment> selectListComment(Integer id,Long blogId);

    //获取父级评论
    List<Comment> getParentComment(Integer parentId,Long blogId);

    //获取一级子回复
    List<Comment> getReplyComment(Integer id);

    //获取所有子集回复
    List<Comment> getAllChildComment(Integer id);

    //保存评论
    void saveComment(Comment comment);
}
