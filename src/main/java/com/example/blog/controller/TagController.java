package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 9:54
        *Description:标签管理
        *
        **/

import com.example.blog.pojo.Tag;
import com.example.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    /*----去分页列表----*/
    /*-----包含一页五个数据的显示-----*/
    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue ="1") Integer pageNum,
                        @RequestParam(defaultValue = "5")Integer pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(tagService.getAllTags());
        model.addAttribute("pageInfo",pageInfo);

        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags",tags);
        System.out.println("去tags页面");
        return "admin/tags";
    }
    /*------去分页新增页面-------*/
    @GetMapping("/inputtag")
    public String inputtag(){
        return "admin/tags-input";
    }

    /*----增加标签-----*/
    @PostMapping("/tag/add")
    public String addTag(Tag tag, RedirectAttributesModelMap redirectAttributesModelMap){
        //这一段可以代码优化
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1!=null){
            redirectAttributesModelMap.addFlashAttribute("message","已存在相同标签，无需添加！");
            return "redirect:/admin/inputtag";
        }
        else {
            tagService.saveTag(tag);
            redirectAttributesModelMap.addFlashAttribute("message","成功添加！");
            return "redirect:/admin/tags";
                }

        /*  List<tag> tags = tagService.getAlltags();
        *//*存储前的tags*//*
        System.out.println(tags);
        if(tags!=null){
            System.out.println("标签中已经存在值");
            for (tag tag1:tags) {
                if (tag1.getName().equals(tag.getName())){
                    redirectAttributesModelMap.addFlashAttribute("message","已有相同的标签");
                    return "redirect:/admin/inputtag";
                }else {

                    tagService.savetag(tag);
                    *//*存储后的tags*//*
                    System.out.println(tags);
                    return "redirect:/admin/tags";
                }
            }
        }

        tagService.savetag(tag);
        System.out.println("已经储存");
        return "redirect:/admin/tags";*/
    }
    /*-------删除标签-------*/
    @GetMapping("/tag/delete/{id}")
    public String deletetag(@PathVariable Integer id){
        tagService.deleteTag(id);
        System.out.println("删除这个tag");
        return "redirect:/admin/tags";
    }
    /*------获取id去修改标签页面--------*/
    @GetMapping("/tag/update/{id}")
    public String updatetag(@PathVariable Integer id,
                                RedirectAttributesModelMap redirectAttributesModelMap,
                                Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }
    /*---------修改标签--------*/
    @PostMapping("/tag/update")
    public  String altertag(Tag tag){

        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

}
