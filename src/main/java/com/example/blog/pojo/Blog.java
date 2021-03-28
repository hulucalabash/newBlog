package com.example.blog.pojo;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-18 15:21
        *Description:博客类
        *
        **/

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component

public class Blog {
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String first_picture;

    private String flag;
    //是否发布
    private Integer published;
    //浏览次数
    private Integer views;
   //是否开启赞赏
    private Integer appreciation;
   //是否可以转载
    private Integer share_statement;

   //是否开启评论
    private Integer commentable;
   //是否推荐
    private Integer recommend;
    private Date create_time;
    private Date update_time;

    private Integer type_id;
    private Integer user_id;
    private String description;
    private List<Integer> tag_ids;

    private Type type;
    private User user;
    private List<Tag>tags;

    public List<Integer> getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(List<Integer> tag_ids) {
        this.tag_ids = tag_ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirst_picture() {
        return first_picture;
    }

    public void setFirst_picture(String first_picture) {
        this.first_picture = first_picture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Integer appreciation) {
        this.appreciation = appreciation;
    }

    public Integer getShare_statement() {
        return share_statement;
    }

    public void setShare_statement(Integer share_statement) {
        this.share_statement = share_statement;
    }

    public Integer getCommentable() {
        return commentable;
    }

    public void setCommentable(Integer commentable) {
        this.commentable = commentable;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", first_picture='" + first_picture + '\'' +
                ", flag='" + flag + '\'' +
                ", published=" + published +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", share_statement=" + share_statement +
                ", commentable=" + commentable +
                ", recommend=" + recommend +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", type_id=" + type_id +
                ", user_id=" + user_id +
                ", description='" + description + '\'' +
                ", tag_ids=" + tag_ids +
                ", type=" + type +
                ", user=" + user +
                ", tags=" + tags +
                '}';
    }
}
