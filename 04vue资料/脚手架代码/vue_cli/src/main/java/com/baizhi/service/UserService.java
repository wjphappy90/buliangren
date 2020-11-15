package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void update(User user);

    void delete(String id);

    List<User> findAll();

    User finById(String id);
}
