package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@Controller
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    //处理编辑操作
    @RequestMapping("edit")
    @ResponseBody
    public void edit(Emp emp,String oper){

        //处理添加
        if(StringUtils.equals("add",oper)){
            empService.save(emp);
        }
        //处理修改
        if(StringUtils.equals("edit",oper)){
            empService.update(emp);
        }
        //处理删除
        if(StringUtils.equals("del",oper)){
            empService.delete(emp.getId());
        }



    }

    //查询所有
    @RequestMapping("findAll")
    @ResponseBody  //jackson  gson  //jqgrid  table 分页  key  total 总页数  page 当前页  rows:当前页数据集  records 总记录数
    public Map<String,Object> findAll(String searchField,String searchString,String searchOper,Integer page,Integer rows,Boolean _search){
        //不做搜索处理
        Map<String,Object> map =  new HashMap<>();
        List<Emp> lists = null;
        Long totalCounts = null;
        if(_search){
            //根据搜索条件查询集合
            lists = empService.findSearch(searchField,searchString,searchOper,page,rows);
            //根据搜索条件查询总条数
            totalCounts = empService.findTotalCountsSearch(searchField,searchString,searchOper);
        }else{
            //进行分页查询
            lists = empService.findAll(page,rows);
            //获取总记录数
            totalCounts = empService.findTotalCounts();
        }
        //公共代码
        Long totalPage = totalCounts%rows==0?totalCounts/rows:totalCounts/rows+1;
        map.put("rows",lists);
        map.put("total",totalPage);
        map.put("page",page);
        map.put("records",totalCounts);
        return map;
    }


}
