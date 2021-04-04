package com.example.blog.service;

        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-13 16:02
        *Description:用户验证的接口
        *
        **/

import com.example.blog.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     /*检查用户名与密码是否正确*/
     User checkUser(String username,String password);
     /*根据id获取user*/
     User getUserById(Integer id);


}
