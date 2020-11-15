package com.baizhi.service;

import com.baizhi.entity.Poem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PoemService {

    //根据关键字查询
    List<Poem> findByKeywords(String content,String type,String author);

    //前台分页查询
    Page<Poem> findAll(Integer page, Integer size);

    //查询所有
    List<Poem> findAll();


    //后台分页查询所有
    List<Poem> findByPage(Integer page,Integer size);
    //后台查询所有记录数
    Long findTotalCounts();
}
