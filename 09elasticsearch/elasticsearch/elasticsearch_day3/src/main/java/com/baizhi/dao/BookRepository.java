package com.baizhi.dao;

import com.baizhi.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by HIAPAD on 2019/11/20.
 */
//使用自定义接口继承ElasticsearchRepository  泛型:当前操作对象类型  泛型:对象中主键类型
public interface BookRepository extends ElasticsearchRepository<Book,String> {

    //自定义方法声明 根据nand content
    List<Book> findByNameAndContent(String nameKeyword,String contentKeyword);

    //自定义方法声明 根据 name  and  content and author
    List<Book> findByNameAndContentAndAuthor(String nameKeyword,String contentKeyword,String authorKeyword);

    //自定义方法声明 根据name or content
    List<Book> findByNameOrContent(String nameKeyword,String contentKeyword);

    //自定义方法 等值查询
    List<Book> findByPrice(Double price);

    //自定义范围查询
    List<Book> findByPriceBetween(Double min,Double max);



}
