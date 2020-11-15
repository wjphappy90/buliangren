package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by HIAPAD on 2019/11/12.
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class BootHomeWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootHomeWorkApplication.class,args);
    }
}
