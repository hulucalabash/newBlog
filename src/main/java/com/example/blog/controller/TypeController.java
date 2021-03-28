package com.example.blog.controller;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 9:54
        *Description:标签管理
        *
        **/

import com.example.blog.pojo.Type;
import com.example.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    /*----去分页列表----*/
    /*-----包含一页五个数据的显示-----*/
    @GetMapping("/types")
    public String types(@RequestParam(defaultValue ="1") Integer pageNum,
                        @RequestParam(defaultValue = "5")Integer pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(typeService.getAllTypes());
        model.addAttribute("pageInfo",pageInfo);

        List<Type> types = typeService.getAllTypes();
        model.addAttribute("types",types);
        System.out.println("去types页面");
        return "admin/types";
    }
    /*------去分页新增页面-------*/
    @GetMapping("/inputtype")
    public String inputType(){
        return "admin/types-input";
    }

    /*----增加分类-----*/
    @PostMapping("/type/add")
    public String addType(Type type, RedirectAttributesModelMap redirectAttributesModelMap){
        //这一段可以代码优化
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1!=null){
            redirectAttributesModelMap.addFlashAttribute("message","已存在相同标签，无需添加！");
            return "redirect:/admin/inputtype";
        }
        else {
            typeService.saveType(type);
            redirectAttributesModelMap.addFlashAttribute("message","成功添加！");
            return "redirect:/admin/types";
                }

        /*  List<Type> types = typeService.getAllTypes();
        *//*存储前的types*//*
        System.out.println(types);
        if(types!=null){
            System.out.println("分类中已经存在值");
            for (Type type1:types) {
                if (type1.getName().equals(type.getName())){
                    redirectAttributesModelMap.addFlashAttribute("message","已有相同的标签");
                    return "redirect:/admin/inputtype";
                }else {

                    typeService.saveType(type);
                    *//*存储后的types*//*
                    System.out.println(types);
                    return "redirect:/admin/types";
                }
            }
        }

        typeService.saveType(type);
        System.out.println("已经储存");
        return "redirect:/admin/types";*/
    }
    /*-------删除分类-------*/
    @GetMapping("/type/delete/{id}")
    public String deleteType(@PathVariable Integer id){
        typeService.deleteType(id);
        System.out.println("删除这个type");
        return "redirect:/admin/types";
    }
    /*------获取id去修改分类页面--------*/
    @GetMapping("/type/update/{id}")
    public String updateType(@PathVariable Integer id,
                                RedirectAttributesModelMap redirectAttributesModelMap,
                                Model model){
        Type type = typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "admin/types-input";
    }
    /*---------修改分类--------*/
    @PostMapping("/type/update")
    public  String alterType(Type type){

        typeService.updateType(type);
        return "redirect:/admin/types";
    }

}
