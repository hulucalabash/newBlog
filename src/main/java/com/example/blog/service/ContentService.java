package com.example.blog.service;
        /*
        *
        *Copyright:hulu有限gh big company
        *Date:2021-05-22 15:41
        *Description:jd爬取数据服务层
        *
        **/

import com.alibaba.fastjson.JSON;
import com.example.blog.pojo.Content;
import com.example.blog.utils.HtmlParseUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


//将数据放入es库中
    public Boolean parseContent(String keywords) throws IOException {
        List<Content> contents = new HtmlParseUtil().parseJD(keywords);
        //将数据批量放入es中
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("2m");

        for (int i = 0; i < contents.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(contents.get(i)), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(!bulk.hasFailures());
        return !bulk.hasFailures();
    }
    //将数据进行查询
    public List<Content> SearchGoods(String keywords,int pageNum,int pageSize) throws IOException, InvocationTargetException, IllegalAccessException {
        //设置请求request
        SearchRequest request = new SearchRequest("jd_goods");
        //构建请求条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页
        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        //用QueryBuilders工具类设置精确查询termquery
        TermQueryBuilder termQuery = QueryBuilders.termQuery("title", keywords);
        //将termquery置入查询条件中
        searchSourceBuilder.query(termQuery);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        //将字段设置为高亮
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        //request.source执行搜索
        request.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        ArrayList<Map<String,Object>> goodlist=new ArrayList<>();
        ArrayList<Content> goodlists=new ArrayList<>();
        for (SearchHit hit:searchResponse.getHits().getHits()
             ) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title = highlightFields.get("title");
            if (title!=null){
                Text[] fragments = title.fragments();
                String n_title="";
                for (Text fragment:fragments
                     ) {
                    n_title+=fragment;

                }
                //将高亮的n_title与title进行替换
                sourceAsMap.put("title",n_title);
            }
            goodlist.add(sourceAsMap);
            Content content = new Content();
            BeanUtils.populate(content,sourceAsMap);
            goodlists.add(content);

            //System.out.println(content);
            //System.out.println("==============================");
            //System.out.println(hit);
        }
        return goodlists;
    }
}


