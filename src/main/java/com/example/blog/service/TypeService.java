package com.example.blog.service;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 16:47
        *Description:分类管理
        *
        **/

import com.example.blog.mapper.TypeMap;
import com.example.blog.pojo.Type;
import com.example.blog.pojo.TypeTop;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TypeService {

    /*增加分类*/
    void saveType(Type type);

    /*删除分类*/
    void deleteType(Integer id);

    /*修改分类*/
    void updateType(Type type);

    /*查询分类*/
    List<Type> getAllTypes();
    Type getTypeById(Integer id);
    Type getTypeByName(String name);
    List<TypeTop> getTypeTop(Integer num);

}
