package com.example.blog.service.impl;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-03-16 17:02
        *Description:分类管理
        *
        **/

import com.example.blog.mapper.TypeMap;
import com.example.blog.pojo.Type;
import com.example.blog.pojo.TypeTop;
import com.example.blog.service.TypeService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    public TypeMap typeMap;
    /*增加type*/
    @Override
    public void saveType(Type type) {
        typeMap.saveType(type);
    }
    /*删除分类*/
    @Override
    public void deleteType(Integer id) {
        typeMap.deleteType(id);
    }

    /*修改分类*/
    @Override
    public void updateType(Type type) {
        typeMap.updateType(type);
    }

    /*获取所有分类*/
    @Override
    public List<Type> getAllTypes() {
        List<Type> types = typeMap.getAllTypes();
        return types;
    }
    /*根据id获取type*/
    @Override
    public Type getTypeById(Integer id) {
        Type type = typeMap.getTypeById(id);
        return type;
    }
    /*根据name获取type*/
    @Override
    public Type getTypeByName(String name) {
        return null;
    }

    /*获得首页推荐的type*/
    @Override
    public List<TypeTop> getTypeTop(Integer num) {
        List<TypeTop> typeTop = typeMap.getTypeTop(num);
        return typeTop;
    }
}
