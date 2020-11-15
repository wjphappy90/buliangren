package com.baizhi;

import com.baizhi.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by HIAPAD on 2019/10/31.
 */
@SpringBootApplication
@Import(User.class)
public class Application {




    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //指定入口类类对象即可
        return builder.sources(Application.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }


}
