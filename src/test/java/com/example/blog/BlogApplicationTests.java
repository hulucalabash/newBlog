package com.example.blog;

import com.example.blog.pojo.Blog;
import com.example.blog.pojo.Content;
import com.example.blog.pojo.TagTop;
import com.example.blog.pojo.TypeTop;
import com.example.blog.service.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    RestHighLevelClient restHighLevelClient;
    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;
    @Autowired
    ContentService contentService;
    @Test
    void contextLoads() {
        List<TypeTop> typeTop = typeService.getTypeTop(3);
        for (TypeTop type:typeTop){
            System.out.println(type);
        }

    }

    @Test
    void createIndexTest() throws IOException {
        // 1. 创建索引请求
        CreateIndexRequest firstIndex = new CreateIndexRequest("xununan_index");

        // 2. 客户端执行创建索引的请求
        CreateIndexResponse response = restHighLevelClient.indices().create(firstIndex, RequestOptions.DEFAULT);

        System.out.println(response);
    }

    @Test
    void SearchGoods() throws IOException, InvocationTargetException, IllegalAccessException {
        List<Content> maps = contentService.SearchGoods("java", 1, 5);
      /*  for (Map<String, Object> map:maps
             ) {
            System.out.println(map);
        }*/
    }



}
