package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-10 12:31
        *Description:评论控制类
        *
        **/

import com.example.blog.pojo.Comment;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/comments/{blog_id}")
    public String comments(@PathVariable Long blog_id, Model model){
        System.out.println(blog_id);

        /*List<Comment> comments = commentService.selectListComment(blog_id);*/
        /*获得父级评论*/
        List<Comment> parentComments= commentService.getParentComment(-1, blog_id);
        for (Comment parentComment:parentComments
             ) {

            List<Comment> replyComments = commentService.selectListComment(parentComment.getId(),blog_id);
            parentComment.setReplyComments(replyComments);

        }

        model.addAttribute("parentComments",parentComments);
        return "blog::commentList";
    }


    @PostMapping("/comments/add")
    public String addComments(Model model,Comment comment){
        Long blog_id = comment.getBlog_id();
        System.out.println("输出博客id"+blog_id);
        System.out.println("输出email"+comment.getEmail());

        //设置博客
        comment.setBlog(blogService.getBlogById(blog_id));
        //设置日期
        comment.setCreate_time(new Date());
        //设置父级评论
      /*  if (comment.getParent_comment_id()!=-1){
                comment.setParentComment();

        }*/

        commentService.saveComment(comment);
        return "redirect:/comments/"+blog_id;
    }

}
