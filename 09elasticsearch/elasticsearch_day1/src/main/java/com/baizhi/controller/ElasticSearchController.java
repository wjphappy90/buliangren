package com.baizhi.controller;

import com.baizhi.elastic.repository.PoemRepository;
import com.baizhi.entity.Poem;
import com.baizhi.service.PoemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("elastic")
public class ElasticSearchController {

    @Autowired
    private PoemRepository poemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PoemService poemService;

    //重建索引
    @RequestMapping("deleteIndex")
    public void flushIndex() {
        //删除索引
        elasticsearchTemplate.deleteIndex(Poem.class);
        //重建索引
        elasticsearchTemplate.putMapping(Poem.class);
    }

    //重建索引数据
    @RequestMapping("index")
    public Map<String, Object> createIndex() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Poem> all = poemService.findAll();
            poemRepository.saveAll(all);
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
        }
        return result;
    }


    //清空所有文档
    @RequestMapping("deleteAll")
    public Map<String, Object> deleteAll() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            poemRepository.deleteAll();
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
        }
        return result;
    }


    //前台系统根据关键词检索
    @GetMapping("findAllKeywords")
    public List<Poem> findAll(String content, String type, String author) {
        //放入redis
        if (!StringUtils.isEmpty(content)) {
            stringRedisTemplate.opsForZSet().incrementScore("keywords", content, 0.1);
        }
        if(StringUtils.equals("所有",type))type=null;
        if(StringUtils.equals("所有",author))author=null;
        System.out.println("type: "+type+"author: "+author);
        List<Poem> poems = poemService.findByKeywords(content, type, author);
        return poems;
    }

    //后台系统查询所有
    @GetMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        page = page == null ? 1 : page;
        rows = rows == null ? 2 : rows;
        Page<Poem> all = poemService.findAll(page, rows);
        map.put("page", page);
        map.put("rows", all.getContent());
        map.put("total", all.getTotalPages());
        map.put("records", all.getNumber());
        return map;
    }

}
