package com.baizhi.test;


import com.baizhi.entity.Book;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用来测试查询
 */
public class TestElasticSearchQuery {

    private TransportClient transportClient;

    @Before
    public void before() throws UnknownHostException {
        transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.220.3"), 9300));
    }



    //高亮查询
    @Test
    public void testHighLight(){

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .field("name")
                .field("content")
                .requireFieldMatch(false)//关闭检索字段匹配  只有关闭这个选项才可以多字段高亮
                .preTags("<span style='color:red'>")
                .postTags("</span>");

        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
                .setTypes("book")
                .setQuery(QueryBuilders.termQuery("content", "下载"))
                .setPostFilter(QueryBuilders.termQuery("content","下载"))//设置过滤条件
                //.setFrom(0)
                //.setSize(10)
                //.addSort(SortBuilders.fieldSort("name").order(SortOrder.DESC))
                .highlighter(highlightBuilder)//设置高亮
                .get();

        SearchHits hits = searchResponse.getHits();

        SearchHit[] searchHits = hits.getHits();

        List<Book> books =  new ArrayList<>();

        for (SearchHit searchHit : searchHits) {
            Book book = new Book();
            //原始信息
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            //获取高亮之后map
            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();

            //赋值
            book.setId(sourceAsMap.get("id").toString());
            book.setName(sourceAsMap.get("name").toString());
            //判断高亮的map是否含有这个字段
            if(highlightFields.containsKey("name")){
                book.setName(highlightFields.get("name").fragments()[0].toString());
            }
            book.setAge(Integer.valueOf(sourceAsMap.get("age").toString()));
            book.setSex(sourceAsMap.get("sex").toString());
            book.setContent(sourceAsMap.get("content").toString());
            if(highlightFields.containsKey("content")){
                book.setContent(highlightFields.get("content").fragments()[0].toString());
            }

            books.add(book);
        }


        books.forEach(book-> System.out.println(book));


    }





    @Test
    public void testQuery(){
        //term查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "故事");

        //range查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(23).lt(30);
        //prefix 查询
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("content", "故");
        //wildcard 查询
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("content", "精*");

        //ids 查询
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery().addIds("11","10","4");

        //fuzzy查询
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("content", "sooong");

        //boolean查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("content","故事"));

        //多字段查询
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("故事下载", "name", "content");

        //queryString
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("这是一个下载的故事")
                .analyzer("ik_max_word")
                .field("name").field("content");

        testQuery(queryStringQueryBuilder);



    }



    //封装测试查询方法
    public void testQuery(QueryBuilder queryBuilder){
        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
                .setTypes("book")
                .setQuery(queryBuilder).get();

        SearchHits hits = searchResponse.getHits();

        SearchHit[] docSearchHits = hits.getHits();
        for (SearchHit docSearchHit : docSearchHits) {
            System.out.println(docSearchHit.getSourceAsString());
        }

    }

    //查询指定字段返回
    @Test
    public void testSource(){
        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")
                .setTypes("book") //类型
                .setQuery(QueryBuilders.matchAllQuery())//查询条件
                .setFrom(0)  //设置起始条数
                .setSize(2)  //每页显示记录数
                //.setFetchSource("*","name","age") //参数1 获取字段  参数2:排除字段
                .setFetchSource(new String[]{},new String[]{"name","age"})
                .get();

        SearchHits hits = searchResponse.getHits();

        SearchHit[] docSearchHits = hits.getHits();
        for (SearchHit docSearchHit : docSearchHits) {
            System.out.println(docSearchHit.getSourceAsString());
        }
        
    }



}
