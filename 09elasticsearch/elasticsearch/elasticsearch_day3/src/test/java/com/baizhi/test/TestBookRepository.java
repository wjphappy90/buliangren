package com.baizhi.test;

import com.baizhi.ElasticSearchApplication;
import com.baizhi.dao.BookRepository;
import com.baizhi.entity.Book;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class TestBookRepository {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //实现  基于多字段 分词检索  过滤  分页  排序  高亮
    @Test
    public void testSearchPageAndSortAndFitlerAndHigh(){

        //高亮字段
        HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                .requireFieldMatch(false)//关闭检索字段匹配
                .preTags("<span style='color:red'>")
                .postTags("</span>");



        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("dangdang")
                .withTypes("book")
                .withQuery(QueryBuilders.queryStringQuery("时间真是好朋友").field("name").field("content").field("author"))//设置查询条件
                .withFilter(QueryBuilders.rangeQuery("price").lt(30.33))//设置过滤条件
                .withSort(new FieldSortBuilder("price").order(SortOrder.DESC))//执行排序条件
                .withPageable(PageRequest.of(0,2))
                .withHighlightFields(field)
                .build();


        AggregatedPage<Book> books = elasticsearchTemplate.queryForPage(searchQuery, Book.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                List<Book> books = new ArrayList<>();
                //根据searchResponse获取hits
                SearchHits hits = searchResponse.getHits();
                //获取检索结果hits
                SearchHit[] searchHits = hits.getHits();
                //遍历
                for (SearchHit searchHit : searchHits) {
                    Book book = new Book();
                    //获取原始记录
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    //获取高亮map
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();

                    book.setId(sourceAsMap.get("id").toString());
                    book.setName(sourceAsMap.get("name").toString());
                    if(highlightFields.containsKey("name")){
                        book.setName(highlightFields.get("name").fragments()[0].toString());
                    }
                    book.setContent(sourceAsMap.get("content").toString());
                    if(highlightFields.containsKey("content")){
                        book.setContent(highlightFields.get("content").fragments()[0].toString());
                    }
                    book.setAuthor(sourceAsMap.get("author").toString());
                    book.setPrice(Double.valueOf(sourceAsMap.get("price").toString()));
                    book.setPubdate(new Date(Long.valueOf(sourceAsMap.get("pubdate").toString())));
                    books.add(book);
                }
                return new AggregatedPageImpl<T>((List<T>) books);
            }
        });

        books.forEach(book -> System.out.println(book));
    }



    //实现 分页  排序操作
    @Test
    public void testSearchPageAndSort(){

        //排序字段
        SortBuilder sortBuilder = new FieldSortBuilder("price").order(SortOrder.ASC);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("dangdang")//设置查询索引
                .withTypes("book")//设置查询类型
                .withQuery(QueryBuilders.matchAllQuery())//设置查询条件
                .withSort(sortBuilder)//设置排序条件
                .withPageable(PageRequest.of(0,5))//设置分页参数

                .build();

        AggregatedPage<Book> books = elasticsearchTemplate.queryForPage(searchQuery, Book.class);

        books.forEach(book-> System.out.println(book));

    }


    //复杂搜索分页操作
    @Test
    public void testSearch(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery = new NativeSearchQuery(matchAllQueryBuilder)
                .setPageable(PageRequest.of((2-1),2));//设置分页  参数1:就是当前页数 默认第一页从0开始
        Page<Book> page = bookRepository.search(searchQuery);
        page.forEach(p -> System.out.println(p));
    }

    //搜索操作 name and content
    @Test
    public void testFindNameAndContent(){
        //List<Book> books = bookRepository.findByNameAndContent("时光", "下载");
        //List<Book> books = bookRepository.findByPrice(23.23);

        //List<Book> books = bookRepository.findByNameAndContentAndAuthor("时光", "时间", "罗永浩");
        List<Book> books = bookRepository.findByPriceBetween(23.23, 30.0);
        books.forEach(book -> System.out.println(book));

    }



    //删除一个
    @Test
    public void testDelete(){
        //删除所有
        //bookRepository.deleteAll();
        Book book = new Book().setId("1");
        bookRepository.delete(book);
        //bookRepository.deleteById("");
    }


    //根据id查询一个
    @Test
    public void testFindOne(){
        Optional<Book> book = bookRepository.findById("1");
        System.out.println(book.get());
    }

    //查询所有
    @Test
    public void testFindAll(){
        Iterable<Book> all = bookRepository.findAll();
        all.forEach(book-> System.out.println(book));
    }


    //保存|更新一条索引  id 存在更新 不存在添加
    @Test
    public void testsave(){
        //先查询原始数据
        Book book = new Book("3", "小白杨-3", 23.23, new Date(), "羊真的很好吃", "罗永浩");
        bookRepository.save(book);
    }

}
