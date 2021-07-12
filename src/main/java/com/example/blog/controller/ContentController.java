package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-05-22 15:40
        *Description:jd数据爬取控制层
        *
        **/

import com.example.blog.pojo.Content;
import com.example.blog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
public class ContentController {
    @Autowired
    public ContentService contentService;

    @GetMapping("/parseJD/{keyword}")
    public Boolean addJD(@PathVariable String keyword) throws IOException {
        Boolean aBoolean = contentService.parseContent(keyword);
        return aBoolean;
    }

    @GetMapping("/searchJD/{keyword}/{pageNum}/{pageSize}")
    public  String searchJD(@PathVariable String keyword,
                                   @PathVariable int pageNum,
                                   @PathVariable int pageSize,
                                   Model model) throws IOException, InvocationTargetException, IllegalAccessException {
        List<Content> maps = contentService.SearchGoods(keyword, pageNum, pageSize);
        model.addAttribute("goodLists",maps);

        return "search";
    }
}
