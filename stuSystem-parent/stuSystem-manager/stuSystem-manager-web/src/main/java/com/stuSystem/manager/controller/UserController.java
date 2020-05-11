package com.stuSystem.manager.controller;

import com.stuSystem.manager.other.UserFactory;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.other.UserEnum;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.pojo.Teacher;
import com.stuSystem.manager.service.StuService;
import com.stuSystem.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户通用请求处理器
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private StuService stuService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping(value = "/goLoginUI",method = RequestMethod.GET)
    public String goLoginUI(){
       /*
       获取session，检测是否已经登录：
       1、如果已经登录，根据权限跳转到指定特定用户界面
       2、未登录，跳转到登录界面
        */
       return "login";
    }

    /**
     * 用户登录：前端已经检验数据输入的合法性
     * 1、根据用户类型，查询用户是否存在
     *    （1）不存在，统一显示密码错误
     *    （2）存在，根据用户类型跳转到特定的用户界面
     * @param request
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/UserLogin",method =RequestMethod.POST)
    public ModelAndView userLogin(HttpServletRequest request,UserInfo userInfo)throws Exception{
        ModelAndView mad = new ModelAndView();
        switch (userInfo.getIdentity()){
            case 1:
                Student stu = stuService.findStudent(userInfo);
                if(stu!=null){
                    UserFactory.CstmUser user = UserFactory.createUser(UserEnum.STUDENT);
                    user.setUser(stu);
                    user.setUsername(stu.getStuName());
                    request.getSession().setAttribute("User", user);
                    mad.setViewName("/stu/stuPage");
                    return mad;
                }

            case 2:
                Teacher teacher = teacherService.findTeacher(userInfo);
                if(teacher!=null){

                    if(teacher.getTeachLevel()==2){
                        UserFactory.CstmUser user = UserFactory.createUser(UserEnum.TEACHER);
                        user.setUser(teacher);
                        user.setUsername(teacher.getTeachName());
                        request.getSession().setAttribute("User",user);
                        mad.setViewName("/teacher/teacherPage");
                    }else if(teacher.getTeachLevel()==3){
                        UserFactory.CstmUser user = UserFactory.createUser(UserEnum.ADMIN);
                        user.setUser(teacher);
                        user.setUsername(teacher.getTeachName());
                        request.getSession().setAttribute("User",user);
                        mad.setViewName("/admin/adminPage");
                    }
                    return mad;
                }
        }
        mad.addObject("err","密码错误");
        mad.addObject("user",userInfo);
        mad.setViewName("login");
        return  mad;

    }

    /**
     * 用于跳转到指定的个人界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goPersonPage",method = RequestMethod.GET)
    public String goPersonPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFactory.CstmUser<Object> user = (UserFactory.CstmUser<Object>)session.getAttribute("User");
        if(user!=null && user.getUserType()==1){
            return "/stu/stuPage";
        }else if(user!=null && user.getUserType()==2){
            return "/teacher/teacherPage";
        }else if(user!=null && user.getUserType()==3){
            return "/admin/adminPage";
        }
        return "login";
    }
    /**z
     * 用于退出并重定向到主页
     * @param request
     * @return
     */
    @RequestMapping(value = "/userExit",method=RequestMethod.GET)
    public String userExit(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("User");
        return "redirect:/user/goMainUI.action";
    }


    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value = "/goMainUI",method = RequestMethod.GET)
    public String goMainPage(){
        return "mainPage";
    }


}
