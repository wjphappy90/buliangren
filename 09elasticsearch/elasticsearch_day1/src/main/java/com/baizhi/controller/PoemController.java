package com.baizhi.controller;

import com.baizhi.entity.Poem;
import com.baizhi.service.PoemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("poem")
public class PoemController {


    @Autowired
    private PoemService poemService;


    //编辑操作
    public Map<String,Object> edit(String oper){

        if(StringUtils.equals(oper,"add")){

        }
        if(StringUtils.equals(oper,"edit")){

        }
        if(StringUtils.equals(oper,"del")){

        }

        return null;
    }


    //分页查询所有
    @RequestMapping("findByPage")
    public Map<String,Object> findByPage(Integer page, Integer rows){
        Map<String,Object> map = new HashMap<>();
        List<Poem> poems = poemService.findByPage(page, rows);
        Long totalCounts = poemService.findTotalCounts();
        Long totalPage = totalCounts%rows==0?totalCounts/rows:totalCounts/rows+1;
        map.put("page", page);
        map.put("rows", poems);
        map.put("total", totalPage);
        map.put("records",totalCounts );
        return map;
    }



}
