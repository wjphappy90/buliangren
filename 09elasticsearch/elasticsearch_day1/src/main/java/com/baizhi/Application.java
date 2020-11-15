package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.com.baizhi.dao")
public class Application {

    public static void main(String[] args) {
        //System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(Application.class,args);
    }
}
