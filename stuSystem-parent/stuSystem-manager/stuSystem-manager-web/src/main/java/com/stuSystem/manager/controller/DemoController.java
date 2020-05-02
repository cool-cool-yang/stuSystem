package com.stuSystem.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/demo")
@Controller
public class DemoController {

    @RequestMapping("/hello")
    public ModelAndView hello(String info){
        System.out.println("hello");
        ModelAndView mod = new ModelAndView();
        mod.addObject("name",info);
        mod.setViewName("show");
        return mod;
    }
}
