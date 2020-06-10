package com.stuSystem.manager.controller;

import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.custpojo.*;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.other.UserFactory;
import com.stuSystem.manager.pojo.other.myException.UserException;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.service.ScoreService;
import com.stuSystem.manager.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/*
学生请求处理控制器
 */
@RequestMapping("/student")
@Controller
public class StuController {

    @Autowired
    private StuService stuService;


    @Autowired
    private ScoreService scoreService;
    /**
     * JAX请求：通过学号检查学生是否存在：
     * 使用json返回结果："1"表示存在，"1"表示不存在
     * @param stuId
     * @return
     */
    @RequestMapping(value = "/hasStudent",method = RequestMethod.POST)
    public @ResponseBody  String hasStudent(String stuId){
        if(stuId == null || stuId.trim()==""){
            return "1";
        }
        String info="0";
      try{
          if(stuService.findStudentByStuId(stuId) != null){
              info="1";
          }else{
              info="0";
          }

      }catch(Exception e){
          e.printStackTrace();
          info="1";
      }finally {
          return info;
      }
    }

    @RequestMapping(value = "/findOneStu",method = RequestMethod.POST)
    public @ResponseBody Student findOneStuById(String  stuId) throws Exception {
       if(stuId==null || stuId.trim().equals("")){
           return null;
       }else{
           Student student = stuService.findStudentByStuId(stuId);
           if(student!=null){
               return student;
           }else{
               return null;
           }
       }

    }

    /**
     * 插入一条学生信息
     * @param mv
     * @param userInfo
     * @return
     */
    @RequestMapping(value="/insertOneStu",method = RequestMethod.POST)
    public ModelAndView insertOneStu(ModelAndView mv,UserInfo userInfo){
        try{
            stuService.insertOneStuItem(userInfo);
            mv.addObject("infor","插入成功："+userInfo.getUserId());
        }catch(UserException e){
            mv.addObject("infor","插入失败："+e.getMessage());
            mv.addObject("importStu",userInfo);
        }
        System.out.println(userInfo);
        mv.setViewName("admin/stuImport");
        return mv;
    }

    /**
     * 以excel表的形式插入学生信息
     * @param mv
     * @param mFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/insertStuTable",method = RequestMethod.POST)
    public ModelAndView insertStuTable(ModelAndView mv ,MultipartFile mFile) throws Exception {
        System.out.println("文件名称："+mFile.getName());
        System.out.println("文件大小："+mFile.getSize());
        List<String> strList;
        try{
            ExcelUser<Student> studentExcelUser = stuService.insertStuTable(mFile);
            StringBuilder builder = new StringBuilder();
            if(studentExcelUser!=null){
                builder.append("总共搜索到学生记录："+studentExcelUser.getTotal()+"<br/>");
                builder.append("成功处理记录："+studentExcelUser.getSuccessCount()+"<br/>");
                if(studentExcelUser.getFailImport()!=null){
                    builder.append("失败导入学生记录："+studentExcelUser.getFailImport().size()+"<br/>");
                    for(Student stu:studentExcelUser.getFailImport()){
                        builder.append(stu.getStuId()+",");
                    }

                }

            }
            mv.addObject("tableInfo",builder.toString());
        }catch(UserException e){
            e.printStackTrace();
            mv.addObject("tableInfo","访问数据库异常");
        }
        mv.setViewName("admin/stuImport");
        return mv;
    }

    /**
     * 跳转到学生信息修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goModifyInfoUI",method = RequestMethod.GET)
    public String goModifyInfoUI(HttpServletRequest request){
        if(isLogin(request)){
            return "stu/stuchangeInfo";
        }else{
            return "login";
        }
    }

    /**
     * 修改学生信息
     * 失败：转发到提示页面
     * 成功：跳转到登录界面
     * @param request
     * @param newSinfo
     * @return
     */
    @RequestMapping(value = "/ModifyTeaInfo",method = RequestMethod.POST)
    public ModelAndView ModifyTeaInfo(HttpServletRequest request,Student newSinfo){
        /*
        重置实体Bean
         */
        HttpSession session = request.getSession();
        UserFactory.CstmUser<Student> user  = (UserFactory.CstmUser<Student>)session.getAttribute("User");
        Student oldS = user.getUser();
        oldS.setStuPwd(newSinfo.getStuPwd());
        oldS.setStuMobile(newSinfo.getStuMobile());
        oldS.setStuEmail(newSinfo.getStuEmail());

        /*
        更新教师信息，移除session中的user属性
         */
        ModelAndView mv = new ModelAndView();
        boolean isSuccess = stuService.updateStuInfo(oldS);
        if(isSuccess){
            session.removeAttribute("User");
            mv.setViewName("login");
        }else{
            mv.addObject("msg","信息修改失败");
            mv.setViewName("metion");
        }
        return mv;
    }

    @RequestMapping(value = "/gradeShow",method = RequestMethod.GET)
    public ModelAndView gradeShow(@RequestParam(required = false,defaultValue = "1") int startPage,
                                  HttpServletRequest request){
        UserFactory.CstmUser<Student> user = (UserFactory.CstmUser<Student>) request.getSession().getAttribute("User");
        PageInfo<CstmScores> pages = scoreService.findScoresPage(startPage,user.getUser().getStuId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("Pages",pages);
        mv.setViewName("stu/gradeshow");
        return mv;
    }


    @RequestMapping(value = "/gradeAnalyse",method = RequestMethod.GET)
    public ModelAndView gradeAnalyse(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
       if(isLogin(request)){
           UserFactory.CstmUser<Student> loginUser =
                   (UserFactory.CstmUser<Student>) request.getSession().getAttribute("User");
           CstmAnalyse cstmAnalyse = scoreService.findOneStuAnalyseByStuId(loginUser.getUser().getStuId());
           System.out.println(cstmAnalyse);
           mv.addObject("ana",cstmAnalyse);
           mv.setViewName("stu/gradeAnalyse");
           return mv;
        }else{
            mv.setViewName("login");

        }
        return mv;
    }
    /**
     * 判断是否登陆
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFactory.CstmUser user = (UserFactory.CstmUser<Object>) session.getAttribute("User");
        if(user!=null && user.getUserType() == 1){
            return true;
        }
        return false;
    }

}
