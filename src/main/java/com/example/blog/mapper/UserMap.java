package com.example.blog.mapper;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-14 10:13
        *Description:用户登录
        *
        **/

import com.example.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//可以使用sql语句
@Repository//存储到spring仓库中便于扫描
public interface UserMap {

    /*获取用户列表*/
    @Select("Select * from user")
    List<User> getusers();

    @Select("Select * from user where id=#{id}")
    User getUserById(Integer id);

}
