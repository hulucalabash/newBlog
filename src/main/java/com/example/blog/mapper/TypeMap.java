package com.example.blog.mapper;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 22:38
        *Description:标签的处理
        *
        **/

import com.example.blog.pojo.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMap {
    /*增加type*/
    @Insert("Insert into type(id,name)values(#{id},#{name})")
    void saveType(Type type);

    /*查询所有type*/
    @Select("Select * from type")
    List<Type> getAllTypes();

    /*删除type*/
    @Delete("Delete from type where id=#{id} ")
    void deleteType(Integer id);

    /*修改type*/
    @Update("Update type set name=#{name} where id=#{id}")
    void updateType(Type type);

    /*根据Id获取type*/
    @Select("Select * from type where id=#{id}")
    Type getTypeById(Integer id);

    /*根据name获取type*/
    @Select("Select * from type where name=#{name}")
    Type getTypeByName(String name);
}
