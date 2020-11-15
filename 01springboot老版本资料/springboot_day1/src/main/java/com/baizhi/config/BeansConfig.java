package com.baizhi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

//一次性在工厂中批量创建多个对象
@Configuration //工厂配置文件 配置注解
public class BeansConfig {


    //工厂中每一个方法代表一个对象的创建 Calendar
    @Bean //注解 修饰范围用在方法上相当于 spring.xml bean标签作用   用来创建这个对象在工厂一个实例
    public Calendar getCalendar(){
        return Calendar.getInstance();
    }


    @Bean
    @Scope("prototype")
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        return connection;
    }



}
