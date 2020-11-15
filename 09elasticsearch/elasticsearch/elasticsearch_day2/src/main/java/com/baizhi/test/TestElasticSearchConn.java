package com.baizhi.test;


import com.baizhi.entity.Book;
import com.google.gson.Gson;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by HIAPAD on 2019/11/19.
 */
public class TestElasticSearchConn {

    private TransportClient transportClient;

    @Before
    public void before() throws UnknownHostException {
        transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.220.3"), 9300));
    }


    //检索
    @Test
    public void testQuery(){

        //查询所有
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        //term查询
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("content", "下载");


        SearchResponse searchResponse = transportClient.prepareSearch("dangdang")//设置索引
                .setTypes("book")//设置类型
                .setQuery(termQueryBuilder)//设置查询条件
                .setSize(2)//每次返回记录
                .setFrom(0)//从哪开始
                .get();

        SearchHits hits = searchResponse.getHits();
        System.out.println("获取总记录数: "+hits.getTotalHits());
        System.out.println("获取最大分数: "+hits.getMaxScore());
        //获取所有文档
        SearchHit[] documents = hits.getHits();
        for (SearchHit document : documents) {
            System.out.println("文档内容: "+document.getSourceAsString());
        }


    }


    //批量更新
    @Test
    public void testBulk(){

        //索引一条记录
        Book book = new Book().setId("10").setAge(23).setSex("女").setName("下载依赖问题-10").setContent("今天我们下载依赖下了好长时间");
        IndexRequest indexRequest = new IndexRequest("dangdang","book",book.getId());
        indexRequest.source(new Gson().toJson(book),XContentType.JSON);

        //删除一条记录
        DeleteRequest deleteRequest = new DeleteRequest("dangdang", "book", "3");

        //更新记录
        Book updateBook = new Book().setId("4").setName("下载依赖问题-4-update");
        UpdateRequest updateRequest = new UpdateRequest("dangdang", "book", updateBook.getId());
        updateRequest.doc(new Gson().toJson(updateBook),XContentType.JSON);

        BulkResponse bulkItemResponses = transportClient.prepareBulk()
                .add(indexRequest)
                .add(deleteRequest)
                .add(updateRequest)
                .get();

        BulkItemResponse[] items = bulkItemResponses.getItems();
        for (BulkItemResponse item : items) {
            System.out.println(item.status());
        }
    }

    //删除一个
    @Test
    public void testDelete(){
        DeleteResponse deleteResponse = transportClient.prepareDelete("dangdang", "book", "1").get();
        System.out.println(deleteResponse.status());
    }

    //查询一个
    @Test
    public void testFindOne(){
        GetResponse getResponse = transportClient.prepareGet("dangdang", "book", "1").get();
        String sourceAsString = getResponse.getSourceAsString();
        System.out.println(sourceAsString);
        //json反序列化
    }



    @Test
    //更新索引
    public void testUpdateIndex() throws UnknownHostException {

        //创建ES客户端
        TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.220.3"), 9300));

       /* Map<String,Object> result = new HashMap<>();
        result.put("name","小黑的故事-1");*/
        Book book = new Book().setId("6dc9f42f-a8f4-47f1-847c-29344551dd03").setName("下载依赖问题-1");
        String json = new Gson().toJson(book);
        //更新文档
        UpdateResponse updateResponse = transportClient.prepareUpdate("dangdang", "book", book.getId())
                .setDoc(json,XContentType.JSON)
                .get();

        System.out.println(updateResponse.status());


    }


    @Test
    public void testCreateIndex() throws UnknownHostException {
      //创建ES客户端 TransportClient  hostip  port 9300
      TransportClient transportClient = new PreBuiltTransportClient(Settings.EMPTY)
              .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.220.3"),9300));

      //索引一条记录到ES  如果指定id 使用指定id作为文档_id  如果不指定id为自动生成_id
        Book book = new Book().setId("13").setAge(23).setSex("女").setName("小黑的故事").setContent("spring故事精选");
        Gson gson = new Gson();
        String json = gson.toJson(book);

       /* Map<String,Object> result = new HashMap<>();
        result.put("name","小黑的故事");
        result.put("sex","男");
        result.put("age",23);
        result.put("content","小黑的故事,小黑今天特别开心!");*/

        IndexResponse indexResponse = transportClient.prepareIndex("dangdang", "book","13")
                .setSource(json, XContentType.JSON) //设置数据  设置请求类型为json格式
                .get();//发送请求到ES

        System.out.println(indexResponse.status());
    }

}
