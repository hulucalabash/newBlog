package com.example.blog;

import com.example.blog.pojo.TypeTop;
import com.example.blog.service.TypeService;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        List<TypeTop> typeTop = typeService.getTypeTop(3);
        for (TypeTop type:typeTop){
            System.out.println(type);
        }

    }

    @Test
    void getUser(){

    }

}
