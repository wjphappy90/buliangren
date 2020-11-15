package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by HIAPAD on 2019/10/30.
 */
//访问控制器路径: localhost:8989/hello/hello
    //springboot 的应用默认没有项目名
@RestController //将类中所有控制器方法返回值转为json格式并响应前端浏览器
@RequestMapping("hello")
public class HelloController {


    @Value("${names}")
    private String name;

    @Value("${server.port}")
    private String port;

    @Value("${price}")
    private Double price;
    @Value("${age}")
    private Integer age;
    @Value("${qqs}")
    private String[] qqs;
    @Value("#{'${strs}'.split(',')}")
    private List<String> strs;

    @Value("#{${maps}}")
    private Map<String,String> maps;

    @Autowired
    private User user;

    @Autowired
    private HelloService helloService;

    @Autowired
    private Calendar calendar;

    @Autowired
    private Connection connection;

    @Autowired
    private Connection connection1;


    @RequestMapping("hello")
    public String hello(){
        System.out.println("姓名: "+name);
        System.out.println("port: "+port);
        System.out.println("年龄:"+age);
        System.out.println("价格: "+price);
        for (String qq : qqs) {
            System.out.println(qq);
        }
        System.out.println("遍历list集合");
        strs.forEach(str -> System.out.println(str));
        System.out.println("遍历maps:");
        maps.forEach((k,v)-> System.out.println("key:"+k+" value:"+v));
        System.out.println("user: "+user);
        System.out.println("user中map");
        user.getMaps().forEach((k,v)-> System.out.println("userkey: "+k+" uservalue: "+v));

        helloService.hello("springboot");
        System.out.println("calendar: "+calendar.getTime());
        System.out.println("connection: "+connection);
        System.out.println("connection: "+connection1);

        System.out.println("hello springboot");
        return "hello springboot";
    }


    @RequestMapping("hello1")
    public String hello1(){
        System.out.println("hello1 springboot");
        return "hello1 springboot";
    }


    @RequestMapping("hello2")
    public String hello2(){
        System.out.println("hello2 springboot");
        return "hello1 springboot";
    }
}
