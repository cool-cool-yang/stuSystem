package com.stuSystem.manager.controller;

import com.stuSystem.manager.custpojo.CstmClassInfo;
import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.mapper.StudentMapper;
import com.stuSystem.manager.pojo.other.UserFactory;
import com.stuSystem.manager.pojo.other.usercheck.StudentIdCheckTool;
import com.stuSystem.manager.pojo.Announce;
import com.stuSystem.manager.pojo.Courser;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.Teachcourse;
import com.stuSystem.manager.service.*;
import com.stuSystem.commons.tools.filesave.FileDealToLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AnnounceService announceService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherCourseService teacherCourseService;
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private  StuService stuService;
    /**
     * 跳转到学生信息导入界面
     * @param request
     * @return
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
     * 跳转到学生搜索界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goStuSearchUI",method = RequestMethod.GET)
    public String goStuSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/stuSearch";
        }
        return "login";
    }

    /**
     * 跳转到老师搜索界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goTeaSearchUI",method = RequestMethod.GET)
    public String goTeaSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/teaSearch";
        }
        return "login";
    }

    /**
     * 跳转公告发布界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goApUI",method = RequestMethod.GET)
    public String goApUI(HttpServletRequest request){

        if(isLogin(request)){
            return "admin/put_announce";
        }
        return "login";
    }
    @RequestMapping(value = "/annoPreUI",method = RequestMethod.GET)
    public String announce_preview(HttpServletRequest request){
       // System.out.println(request.getSession().getServletContext().getRealPath(""));

        if(isLogin(request)){
            return "admin/announce-yulan";
        }
        return "login";
    }

    /**
     * 发布公告并进行保存
     * 1、存储基本细腻
     * 2、处理附件：
     * @param announce
     * @param otherfile
     * @return
     */
    @RequestMapping(value = "/putAnnounce",method=RequestMethod.POST)
    public ModelAndView putAnnounce(Announce announce, MultipartFile otherfile) {
        ModelAndView mv = new ModelAndView();
        System.out.println("我已经进来了");
        System.out.println(otherfile);
        String str = System.getProperty("inf-root");
        if(!(otherfile==null || otherfile.isEmpty())) {
            try {
                FileDealToLocal fileDealToLocal = new FileDealToLocal();
                String linkName = fileDealToLocal.saveFile(otherfile);
                announce.setAnnouLink(linkName);
            } catch (Exception e) {
                e.printStackTrace();
                mv.addObject("msg","公告发布失败");
                mv.setViewName("metion");
                return mv;
            }
        }
        boolean flag = announceService.InsertOneAnnounce(announce);
        if(flag){
            mv.addObject("msg","公告发布成功");
        }else{
            mv.addObject("msg","公告发布失败");
        }
        mv.setViewName("metion");
        return mv;
    }

    /**
     * 跳转到增加课程的界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goNewCourseUI",method = RequestMethod.GET)
    public String goNewCourseUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/newCouse";
        }
        return "login";
    }

    /**
     * 异步查询课程是否存在
     * @param courseName
     * @return
     */
    @RequestMapping(value = "/courseHas",method = RequestMethod.POST)
    @ResponseBody
    public int courseHas(String courseName){
        Courser courser = courseService.findOneCourserByName(courseName);
        if(courser!=null){
            return 1;
        }else{
            return 0;
        }
    }
    /**
     * 插入一条课程信息
     * @param courser
     * @return
     */
    @RequestMapping(value = "insertOneCour",method = RequestMethod.POST)
    public ModelAndView insertOne(Courser courser) throws Exception {
        ModelAndView mv =new ModelAndView();
        boolean flag = courseService.inserOneCourser(courser);
        if(flag){
            mv.addObject("msg","课程导入成功");
        }else{
            mv.addObject("msg","课程发布失败");
        }
        mv.setViewName("metion");
        return mv;
    }

    /**
     *  跳转到添加授课界面:需要携带数据，用于datalist展示
     * @param request
     * @return
     */
    @RequestMapping(value = "/goNewClassUI",method = RequestMethod.GET)
    public ModelAndView goNewClassUI(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        if(isLogin(request)){
            List<Map<String,String>>  allCourseInfo = courseService.findAllCoursers();
            List<Map<String,String>> allTeacherInfo = teacherService.findAllTeachers();

            mv.addObject("ac",allCourseInfo);
            mv.addObject("tc",allTeacherInfo);
            mv.setViewName("admin/newClass");
            return mv;
        }
        mv.setViewName("login");
        return mv;

    }

    /**
     * 插入开课信息
     * @param cstmClassInfo
     * @return
     */
    @RequestMapping(value = "/insertOneClass",method = RequestMethod.POST)
    public ModelAndView insertOneClass(CstmClassInfo cstmClassInfo) throws Exception {

        System.out.println(cstmClassInfo);
        ModelAndView mv = new ModelAndView();
        /*设置跳转页面*/
        mv.setViewName("metion");
        ExcelUser<String> excelUser=null;
        try{
            //处理excel表
            excelUser = StudentIdCheckTool.dealExceltoList(cstmClassInfo.getmFile());
            int stucount;
            StringBuffer buf = new StringBuffer();/* 串接结果信息*/
            if(excelUser==null || excelUser.getTotal()<=0){
                buf.append("该课程没有学生<br/>");
            }
            Teachcourse teachcourse = createTCByInfos(cstmClassInfo);
            teachcourse.setTcStucount(excelUser.getSuccessDeal().size());
            String tcId = teacherCourseService.insetOneTeaCour(teachcourse);
            if(tcId==null){
                buf.append("开课信息导入失败<br/>");
                mv.addObject("msg",buf.toString());
                return mv;
            }
            buf.append("开课id："+tcId+"<br/>");
            //授课信息已经成功导入，处理成绩表
            Date date = new Date();
            List<Scores> scoresList = new ArrayList<>();
            for(String stu:excelUser.getSuccessDeal()){
                Scores scores = new Scores();
                scores.setScoresTcid(tcId);
                //学生存在才插入
                if(stuService.findStudentByStuId(stu)==null){
                    continue;
                }
                scores.setScoresStuid(stu);
                scores.setScoresIntime(date);
                scoresList.add(scores);
            }
            //插入学生信息
            int successCount = scoreService.insertManyItems(scoresList);
            buf.append("成功导入开课信息<br/>学生信息核对情况： 成功导入数量"+successCount);
            mv.addObject("msg",buf.toString());
            buf = null;
            return mv;
        }catch(Exception e){
            /*捕获到异常，直接停止此次操作，提示开课失败*/
           e.printStackTrace();
            mv.addObject("msg","开课失败");
            return mv;
        }
    }
    /**
     * 通过前缀名称进行模糊查询
     * @param courseName
     * @return
     */
    @RequestMapping(value = "/findCoursersByPrename",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,String>> findCoursersByPrename(String courseName){
        return findCoursersByPrename(courseName);
    }

    /**
     * 重新封装开课信息
     * @param cstmClassInfo
     * @return
     */
    private Teachcourse createTCByInfos(CstmClassInfo cstmClassInfo){
        Teachcourse teachcourse = new Teachcourse();
        teachcourse.setTcCourser(cstmClassInfo.getCourseId());
        teachcourse.setTcTeacher(cstmClassInfo.getTeachId());
        return teachcourse;
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




}
