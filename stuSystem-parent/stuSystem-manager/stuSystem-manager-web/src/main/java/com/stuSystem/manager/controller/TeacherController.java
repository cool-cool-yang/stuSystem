package com.stuSystem.manager.controller;


import com.stuSystem.manager.custpojo.CstmScores;
import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.Teachcourse;
import com.stuSystem.manager.pojo.other.myException.UserException;
import com.stuSystem.manager.pojo.other.UserFactory;
import com.stuSystem.manager.pojo.Teacher;
import com.stuSystem.manager.service.ScoreService;
import com.stuSystem.manager.service.TeacherCourseService;
import com.stuSystem.manager.service.TeacherService;
import org.apache.poi.util.Removal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 教师请求处理器
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private ScoreService scoreService;


    /**
     * 通过教师ID查询教师是否存在
     * 存在：返回1
     * 不存在：返回0
     * @param teacId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/hasTeach",method = RequestMethod.POST)
    public @ResponseBody String hasTeacher(String teacId) throws Exception {
        if(teacId==null || teacId.trim().equals("")){
            return "1";
        }
        String info="0";
        try{
            if(teacherService.findTeacherByTeachId(teacId) != null){
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

    /**
     * 如果存在，将教师信息以json形式返回
     * @param teacId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findOneTeacher",method = RequestMethod.POST)
    public  @ResponseBody Teacher findOneStuById(String teacId)throws Exception{
        if(teacId==null || teacId.trim().equals("")){
            return null;
        }else{
            Teacher teacher  = teacherService.findTeacherByTeachId(teacId);
            return teacher;
        }

    }


    /**
     * 插入一条教师记录
     * @param mv
     * @param userInfo
     * @return
     */
    @RequestMapping(value="/insertOneTeach",method = RequestMethod.POST)
    public ModelAndView insertOneStu(ModelAndView mv, UserInfo userInfo){
        System.out.println("已经进入处理器");
        try{
             teacherService.insertOneTeachItem(userInfo);
            mv.addObject("infor","插入成功："+userInfo.getUserId());
        }catch(UserException e){
            mv.addObject("infor","插入失败："+e.getMessage());
            mv.addObject("importTeach",userInfo);
        }
        System.out.println(userInfo);
        mv.setViewName("admin/teaImport");
        return mv;
    }

    /**
     * 以excel表格的形式插入教师信息
     * @param mv
     * @param mFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/insertTeachTable",method = RequestMethod.POST)
    public ModelAndView insertStuTable(ModelAndView mv , MultipartFile mFile) throws Exception {
        System.out.println("文件名称："+mFile.getName());
        System.out.println("文件大小："+mFile.getSize());
        mv.setViewName("admin/teaImport");
        List<String> strList;
        try{
            ExcelUser<Teacher> teacherExcelUser = teacherService.insertTeachTable(mFile);
            StringBuilder builder = new StringBuilder();
            if(teacherExcelUser!=null){
                builder.append("总共搜索到教师记录："+teacherExcelUser.getTotal()+"<br/>");
                builder.append("成功处理记录："+teacherExcelUser.getSuccessCount()+"<br/>");
                if(teacherExcelUser.getFailImport()!=null){
                    builder.append("失败导入教师记录："+teacherExcelUser.getFailImport().size()+"<br/>");
                    for(Teacher teacher:teacherExcelUser.getFailImport()){
                        builder.append(teacher.getTeachId()+",");
                    }
                }

            }
            mv.addObject("tableInfo",builder.toString());
        }catch(UserException e){
            e.printStackTrace();
            mv.addObject("tableInfo","访问数据库异常");
        }
        return mv;
    }

    /**
     * 从教师主页跳转学生补录界面
     * 携带信息：教师所教授的所有课程
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAddStuforTcUI",method = RequestMethod.GET)
    public ModelAndView goAddStuforTcUI(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        if(isLogin(request)){
            //获取登录用户的信息
            UserFactory.CstmUser<Teacher> user =
                    (UserFactory.CstmUser<Teacher>) request.getSession().getAttribute("User");
            List<Map<String,String>> tcInfoList = teacherCourseService.findTcByTeachId(user.getUser().getTeachId());
            mv.addObject("tcInfoList",tcInfoList);
            mv.setViewName("teacher/addStuforTc");
        }else{
            mv.setViewName("login");
        }
        return mv;
    }

    /**
     * 插入一条学生入课信息
     * @param tcId
     * @param stuId
     * @return
     */
    @RequestMapping(value = "/insertOneTcItem",method = RequestMethod.POST)
    public ModelAndView insetOneTcItem(String tcId,String stuId){

        /**
         * 最初设想：需要超级管理员开放数据操作权限，才可以增加数据
         */
        ModelAndView mv = new ModelAndView();
        mv.setViewName("metion");
        try{
            boolean flag = teacherCourseService.inserOneItemforTc(tcId,stuId);
            if(flag){
                mv.addObject("msg","补录信息成功！");
            }else{
                mv.addObject("msg","补录信息失败");
            }
            return mv;
        }catch (Exception e){
            mv.addObject("msg","补录信息失败");
            return mv;
        }
    }

    /**
     * 跳转到信息修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goModifyInfoUI",method = RequestMethod.GET)
    public String goModifyInfoUI(HttpServletRequest request){
        if(isLogin(request)){
            return "teacher/teachangeInfo";
        }else{
            return "login";
        }
    }

    /**
     * 已经登录的教师修改信息
     * @param request
     * @param newtinfo
     * @return
     */
    @RequestMapping(value = "/ModifyTeaInfo",method = RequestMethod.POST)
    public ModelAndView ModifyTeaInfo(HttpServletRequest request,Teacher newtinfo){
        /*
        重置实体Bean
         */
        HttpSession session = request.getSession();
        UserFactory.CstmUser<Teacher> user  = (UserFactory.CstmUser<Teacher>)session.getAttribute("User");
        Teacher oldT = user.getUser();
        oldT.setTeachMobile(newtinfo.getTeachMobile());
        oldT.setTeachEmail(newtinfo.getTeachEmail());
        oldT.setTeachPwd(newtinfo.getTeachPwd());

        /*
        更新教师信息，移除session中的user属性
         */
        ModelAndView mv = new ModelAndView();
        boolean isSuccess = teacherService.updateTeachInfo(oldT);
        if(isSuccess){
            session.removeAttribute("User");
            mv.setViewName("login");
        }else{
            mv.addObject("msg","信息修改失败");
            mv.setViewName("metion");
        }
        return mv;
    }

    /**
     * 跳转到成绩导入界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goImpGradeUI",method = RequestMethod.GET)
    public ModelAndView goImpGradeUI(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        if(isLogin(request)){
            UserFactory.CstmUser<Teacher> user =
                    (UserFactory.CstmUser<Teacher>) request.getSession().getAttribute("User");
            List<Map<String,String>> tcInfoList = teacherCourseService.findTcByTeachId(user.getUser().getTeachId());
            mv.addObject("tcInfoList",tcInfoList);
            mv.setViewName("teacher/gradeImp");
        }else{
            mv.setViewName("login");
        }

        return mv;
    }

    /**
     * 异步请求获得某门授课的所有学生信息
     * @param tcId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/asyncAllStuScores",method = RequestMethod.POST)
    public List<CstmScores> asyncAllStuScores(String tcId){
        return scoreService.selectScoresByTcId(tcId);
    }


    /**
     * 更新单条记录的信息
     * @param scores
     * @return
     */
    @RequestMapping(value = "/updateOneStuGrade",method = RequestMethod.POST)
    public ModelAndView updateOneStuGrade(Scores scores){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("metion");
        boolean flag = scoreService.updateOneStuGrade(scores);
        if(flag){
            StringBuffer buf = new StringBuffer();
            buf.append("更新数据成功！<br/>")
                    .append("学生学号："+scores.getScoresStuid()+"<br/>")
                    .append("课程编号："+scores.getScoresTcid()+"<br/>")
                    .append("分数："+scores.getScoresRes());
            mv.addObject("msg",buf.toString());
        }else{
            mv.addObject("msg","学生信息导入失败");
        }
        return  mv;
    }


    /**
     * 批量导入学生成绩
     * @param scoresStuid
     * @param mFile
     * @return
     */
    @RequestMapping(value = "/updateStuGrasWithExcel",method = RequestMethod.POST)
    public ModelAndView updateStuGrasWithExcel(String scoresStuid,MultipartFile mFile){

       ModelAndView mv = new ModelAndView();
       mv.setViewName("metion");
       if(scoresStuid==null || scoresStuid.length()==0 || "".equals(scoresStuid.trim())){
           mv.addObject("msg","课程编号错误");
       }else{
           ExcelUser<Scores> scoresExcelUser =  scoreService.updateStuGrasWithExcel(scoresStuid,mFile);
           if(scoresExcelUser==null || scoresExcelUser.getFailImport().size()==scoresExcelUser.getSuccessCount()){
               mv.addObject("msg","数据更新错误");
           }else{
               StringBuffer buf = new StringBuffer();
               //总记录减1，因为首行是提示栏
               buf.append("扫描到记录："+(scoresExcelUser.getTotal()-1)+"<br/>")
                       .append("成功处理记录："+(scoresExcelUser.getSuccessCount())+"<br/>")
                       .append("成功导入记录："+
                               (scoresExcelUser.getSuccessCount()-scoresExcelUser.getFailImport().size())+"<br/>");
              if(scoresExcelUser.getFailImport().size()>0){
                  buf.append("导入失败学生的学号：<br/>");
                  for(Scores sc:scoresExcelUser.getFailImport()){
                      buf.append(sc.getScoresStuid()+",");
                  }
              }

               mv.addObject("msg",buf.toString());
           }

       }
        return mv;
    }


    /**
     * 判断教师是否登陆。
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFactory.CstmUser user = (UserFactory.CstmUser<Object>) session.getAttribute("User");
        if(user!=null && user.getUserType() == 2){
            return true;
        }
        return false;
    }

}
