package com.baizhi.service;

import org.springframework.stereotype.Service;

/**
 * Created by HIAPAD on 2019/10/30.
 */
@Service
public class HelloServiceImpl implements  HelloService{
    @Override
    public void hello(String name) {
        System.out.println("hello service: " +name);
    }
}
