package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HIAPAD on 2019/10/30.
 */

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("findAll")
    public String findAll(){
        System.out.println("findAll");
        return "index";
    }
}
