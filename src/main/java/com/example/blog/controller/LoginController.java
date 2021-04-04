package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-14 11:06
        *Description:用户登录
        *
        **/

import com.example.blog.pojo.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    public UserService userService;

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    /*
    * Post方法提交
    *@RequestParam该变量必须要有值
    *
    * */
    @PostMapping("/admin/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session,
                        RedirectAttributesModelMap redirectAttributesModelMap){

        User user = userService.checkUser(username, password);
        session.setAttribute("user",user);
        model.addAttribute("user",user);
        if(user!=null){
            redirectAttributesModelMap.addFlashAttribute("user",user);
            return "admin/index";
        }
            /*重定向中model数据丢失 使用 redirectAttributesModelMap.addFlashAttribute方法*/
            redirectAttributesModelMap.addFlashAttribute("message","用户名或者密码错误！");
            return "redirect:/login";
    }

    @GetMapping("/loginout")
    public String loginout(HttpSession session){

        session.removeAttribute("user");
        return "redirect:/login";
    }

}
