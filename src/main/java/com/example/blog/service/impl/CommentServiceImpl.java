package com.example.blog.service.impl;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-10 15:15
        *Description:评论业务实现类
        *
        **/

import com.example.blog.mapper.CommentMap;
import com.example.blog.pojo.Comment;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMap commentMap;

    private List<Comment> ReplyComments=new ArrayList<>();
    @Override
    public List<Comment> selectListComment(Integer id,Long blogId) {
        List<Comment> parentComments = commentMap.getParentComments(-1, blogId);
        if (parentComments.size()>0&&!parentComments.isEmpty()){
            for (Comment parentComment:parentComments) {
                List<Comment> comments=new ArrayList<>();
                List<Comment> replyComments = commentMap.getReplyComments(id);
                if (replyComments.size()>0&&!replyComments.isEmpty()){
                    for (Comment replyComment:replyComments) {
                        System.out.println(replyComment.getId());
                        replyComment.setParentComment(parentComment);
                        ReplyComments.add(replyComment);
                        addChildComments(replyComment);

                    }
                    for (Comment ReplyComment:ReplyComments) {
                        comments.add(ReplyComment);
                    }
                    ReplyComments=new ArrayList<>();
                    return comments;
                }
            }

        }
        return null;
    }

    public void addChildComments(Comment comment){
        List<Comment> childComments = getAllChildComment(comment.getId());
        if (childComments.size()>0&&!childComments.isEmpty()){
            for (Comment childComment:childComments
            ) {
                System.out.println("调用了add方法");
                childComment.setParentComment(comment);
                ReplyComments.add(childComment);
                addChildComments(childComment);
            }
        }
    }
    @Override
    public List<Comment> getParentComment(Integer parentId, Long blogId) {
        List<Comment> parentComments = commentMap.getParentComments(parentId, blogId);
        return parentComments;
    }

    @Override
    public List<Comment> getReplyComment(Integer id) {
        List<Comment> replyComments = commentMap.getReplyComments(id);
        return replyComments;
    }

    @Override
    public List<Comment> getAllChildComment(Integer id) {
        List<Comment> childComments = commentMap.getAllChildComments(id);
        return childComments;
    }

    @Override
    public void saveComment(Comment comment) {
        commentMap.saveComment(comment);

    }
}
