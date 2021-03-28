package com.example.blog.service.impl;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-13 16:12
        *Description:用户检查
        *
        **/

import com.example.blog.mapper.UserMap;
import com.example.blog.pojo.User;
import com.example.blog.service.UerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UerService {
    @Autowired
    public UserMap userMap;
    @Override
    public User checkUser(String username, String password) {
        List<User> users = userMap.getusers();
        for (User user:users) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
                    return user;
                 }
        }
        return null;


    }
}
