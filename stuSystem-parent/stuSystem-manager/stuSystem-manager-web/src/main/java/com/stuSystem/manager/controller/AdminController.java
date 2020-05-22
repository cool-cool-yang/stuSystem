package com.stuSystem.manager.controller;

import com.stuSystem.manager.other.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {


    /**
     * 跳转到学生信息导入界面
     * @param request
     * @return
     */
    @RequestMapping(value="/goStuImportUI",method= RequestMethod.GET)
    public String goStuImport(HttpServletRequest request){
       if(isLogin(request)){
           return "admin/stuImport";
       }
       return "login";
    }

    /**
     * 跳转到教师信息导入界面
     * @param request
     * @return
     */
   @RequestMapping(value = "/goTeaImporUi",method = RequestMethod.GET)
    public String goSTeaImport(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/teaImport";
        }
        return "login";
    }

    /**
     * 判断超级管理员是否登陆。
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFactory.CstmUser user = (UserFactory.CstmUser<Object>) session.getAttribute("User");
        if(user!=null && user.getUserType() == 3){
            return true;
        }
        return false;
    }
    @RequestMapping(value = "/goStuSearchUI",method = RequestMethod.GET)
    public String goStuSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/stuSearch";
        }
        return "login";
    }
    @RequestMapping(value = "/goTeaSearchUI",method = RequestMethod.GET)
    public String goTeaSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/teaSearch";
        }
        return "login";
    }

}
