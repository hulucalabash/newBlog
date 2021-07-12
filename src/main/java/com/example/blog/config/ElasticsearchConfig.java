package com.example.blog.config;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-05-13 15:16
        *Description:es配置
        *
        **/

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    //elasticsearch配置类
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")

                        /** 多个节点也是在当前地方配置 */
//                        , new HttpHost("localhost", 9300, "http")
                ));
        return client;
    }





}
