package com.stuSystem.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/say")
    public void say(String info){
        System.out.println("hello");
    }
}
