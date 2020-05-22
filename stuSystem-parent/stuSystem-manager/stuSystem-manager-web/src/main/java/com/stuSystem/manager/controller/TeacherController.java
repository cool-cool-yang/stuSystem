package com.stuSystem.manager.controller;


import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Teacher;
import com.stuSystem.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 教师请求处理器
 */
@RequestMapping("/teacher")
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


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
                builder.append("总共搜索到学生记录："+teacherExcelUser.getTotal()+"<br/>");
                builder.append("成功处理记录："+teacherExcelUser.getSuccessCount()+"<br/>");
                if(teacherExcelUser.getFailImport()!=null){
                    builder.append("失败导入学生记录："+teacherExcelUser.getFailImport().size()+"<br/>");
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

}