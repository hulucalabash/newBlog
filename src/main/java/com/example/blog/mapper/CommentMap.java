package com.example.blog.mapper;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-04-10 11:51
        *Description:评论数据库操作
        *
        **/

import com.example.blog.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMap {

    //保存评论
    @Insert("Insert into comment(nickname,email,content,create_time,blog_id,parent_comment_id) " +
            "values(#{nickname},#{email},#{content},#{create_time},#{blog_id},#{parent_comment_id})")
    void saveComment(Comment comment);

    @Select("Select * from comment where blog_id=#{blogId}")
    List<Comment> listComment(Long blogId);
    //查询父级评论
    @Select("Select * from comment c where c.parent_comment_id=#{parentId} and c.blog_id=#{blogId} order by c.create_time desc")
    List<Comment> getParentComments(Integer parentId,Long blogId);

    //查询一级回复
    @Select("Select * from comment c where c.parent_comment_id=#{id} order by c.create_time desc")
    List<Comment> getReplyComments(Integer id);

    //查询二级以及所有子集回复
    @Select("Select * from comment c where c.parent_comment_id=#{childId} order by c.create_time desc")
    List<Comment> getAllChildComments(Integer childId);
}
