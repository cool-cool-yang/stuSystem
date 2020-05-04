package com.stuSystem.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/public")
@Controller
public class BaseController {

    /**
     * 跳转到登录界面
     * 1、检测是否已经登录（AJAx验证）
     * 2、未登录，跳转到登录界面
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String goLoginGUI(HttpServletRequest request){

        return "login";
    }

}
