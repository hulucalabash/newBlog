package com.example.blog.mapper;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 22:38
        *Description:标签的处理
        *
        **/

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Tag;
import com.example.blog.pojo.TagTop;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMap {
    /*增加tag*/
    @Insert("Insert into tag(id,name)values(#{id},#{name})")
    void saveTag(Tag tag);

    /*查询所有tag*/
    @Select("Select * from tag")
    List<Tag> getAllTags();

    /*删除tag*/
    @Delete("Delete from tag where id=#{id} ")
    void deleteTag(Integer id);

    /*修改tag*/
    @Update("Update tag set name=#{name} where id=#{id}")
    void updateTag(Tag tag);

    /*根据Id获取tag*/
    @Select("Select * from tag where id=#{id}")
    Tag getTagById(Integer id);

    /*根据name获取tag*/
    @Select("Select * from tag where name=#{name}")
    Tag getTagByName(String name);

    @Select("SELECT t.id,t.name,count(*) as num FROM blogtag bt,tag t\n" +
            "WHERE  t.id=bt.tag_id\n" +
            "GROUP BY t.name;\n")
    List<TagTop> getTagTop(Integer num);

    @Select("SELECT tag.id,tag.name FROM blog,blogtag,tag WHERE blogtag.blog_id=#{id} AND tag.id=blogtag.tag_id group by tag.id;")
    List<Tag> getBlogTags(Blog blog);
}
