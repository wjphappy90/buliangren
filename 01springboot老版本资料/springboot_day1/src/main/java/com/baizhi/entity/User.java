package com.baizhi.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Created by HIAPAD on 2019/10/30.
 */
@Component
@ConfigurationProperties(prefix = "user")
public class User {

    private String id;

    private String namea;

    private Integer age;

    private Date bir;

    private Map<String,String> maps;

    public Map<String, String> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", namea='" + namea + '\'' +
                ", age=" + age +
                ", bir=" + bir +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamea() {
        return namea;
    }

    public void setNamea(String namea) {
        this.namea = namea;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public User() {
    }

    public User(String id, String namea, Integer age, Date bir) {

        this.id = id;
        this.namea = namea;
        this.age = age;
        this.bir = bir;
    }
}
