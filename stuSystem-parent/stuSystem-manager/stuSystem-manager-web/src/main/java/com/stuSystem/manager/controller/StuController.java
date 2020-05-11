package com.stuSystem.manager.controller;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.nio.Buffer;
import java.util.List;


/*
学生请求处理控制器
 */
@RequestMapping("/student")
@Controller
public class StuController {

    @Autowired
    private StuService stuService;

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
    @RequestMapping(value="/insertStuTable",method = RequestMethod.POST)
    public ModelAndView insertStuTable(ModelAndView mv ,MultipartFile mFile) throws Exception {
        System.out.println("文件名称："+mFile.getName());
        System.out.println("文件大小："+mFile.getSize());
        List<String> strList;
        try{
            ExcelUser<Student> studentExcelUser = stuService.insertStuTable(mFile);
            StringBuilder builder = new StringBuilder();
            builder.append("总共搜索到学生记录："+studentExcelUser.getTotal()+"<br/>");
            if(studentExcelUser!=null){
                builder.append("成功处理记录："+studentExcelUser.getSuccessDeal().size()+"<br/>");
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
}
