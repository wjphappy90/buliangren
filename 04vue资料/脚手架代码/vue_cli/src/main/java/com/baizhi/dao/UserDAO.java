package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDAO{

    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> findAll();

    User finById(String id);
}
