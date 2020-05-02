package com.stuSystem.manager.controller;

import com.stuSystem.manager.pojo.User;
import com.stuSystem.manager.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/demo")
@Controller
public class DemoController {
    @Autowired
    private AService aService;

    @RequestMapping("/hello")
    public ModelAndView hello(String name){
        System.out.println(name);
        User user = aService.name(name);
        ModelAndView mod = new ModelAndView();
        if(user!=null){
            mod.addObject("name",user.getUserId()+":"+user.getUserName());
        }else{
            mod.addObject("name","没有这个人");
        }
        mod.setViewName("show");
        return mod;
    }
}
