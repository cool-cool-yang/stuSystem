package com.stuSystem.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping(value = "/goLoginUI",method = RequestMethod.GET)
    public String goLoginUI(){
       /*
       获取session，检测是否已经登录：
       1、如果已经登录，根据权限跳转到指定特定用户界面
       2、未登录，跳转到登录界面
        */
       return "login";
    }
}
