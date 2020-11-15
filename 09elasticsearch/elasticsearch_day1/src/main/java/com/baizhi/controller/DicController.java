package com.baizhi.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("dic")
public class DicController {



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //加载远程词典
    @RequestMapping(value = "remote",produces = "text/plain")
    public String remote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realPath = request.getServletContext().getRealPath("/");
        File file = new File(realPath, "init.dic");
        String readFileToString = FileUtils.readFileToString(file);
        response.setDateHeader("Last-Modified",System.currentTimeMillis());
        response.setHeader("ETag",DigestUtils.md5DigestAsHex(readFileToString.getBytes()));
        return readFileToString;
    }

    //获取redis热词排行榜
    @RequestMapping("/findRedisKeywords")
    public Set<ZSetOperations.TypedTuple<String>> findRedisKeywords(){
        Set<ZSetOperations.TypedTuple<String>> keywords = stringRedisTemplate.opsForZSet().reverseRangeWithScores("keywords", 0, 10);
        return keywords;
    }

    //添加到远程词典
    @RequestMapping("save")
    public Map<String, Object> saveDic(String keyword,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            String trimAllWhitespace = StringUtils.trimAllWhitespace(keyword);
            //放入词典中
            String realPath = request.getServletContext().getRealPath("/");
            File file = new File(realPath, "init.dic");
            String readFileToString = FileUtils.readFileToString(file);
            if(!readFileToString.contains(trimAllWhitespace)){
                FileUtils.write(file,trimAllWhitespace+"\r\n","UTF-8",true);
                result.put("success",true);
            }else{
                throw new RuntimeException("关键词已经存在,无须重复添加!!!");
            }
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
        }
        return result;
    }

    //删除词典中热词
    @RequestMapping("delete")
    public Map<String, Object> delete(String keyword, HttpServletRequest request) throws IOException {
        Map<String,Object> result = new HashMap<>();
        try{
            String realPath = request.getServletContext().getRealPath("/");
            File file = new File(realPath, "init.dic");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                if (!line.equals(keyword)) {
                    sb.append(line).append("\r\n");
                }
            }
            FileUtils.write(file,sb.toString(),"UTF-8",false);
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
        }
        return result;
    }

    //获取远程词典列表
    @RequestMapping("findAll")
    public List<String> findAllDics(HttpServletRequest request) throws IOException {
        String realPath = request.getServletContext().getRealPath("/");
        File file = new File(realPath, "init.dic");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        while(true){
           String keywords =  bufferedReader.readLine();
           if(keywords==null)break;
           list.add(keywords);
        }
        return list;
    }
}
