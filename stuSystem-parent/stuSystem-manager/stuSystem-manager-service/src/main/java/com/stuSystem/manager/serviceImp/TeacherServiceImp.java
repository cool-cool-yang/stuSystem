package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.mapper.TeacherMapper;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.other.productService.ProductService;
import com.stuSystem.manager.other.usercheck.TeacherCheck;
import com.stuSystem.manager.other.usercheck.UserCheck;
import com.stuSystem.manager.pojo.Teacher;
import com.stuSystem.manager.pojo.TeacherExample;
import com.stuSystem.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service("teacherServiceImp")
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Teacher findTeacher(UserInfo userInfo) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeachIdEqualTo(userInfo.getUsername());
        criteria.andTeachPwdEqualTo(userInfo.getPwd());
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        if(teacherList.size()>0){
            Teacher teacher = teacherList.get(0);
            teacher.setTeachLine(1);
            return teacher;
        }
        return null;
    }

    /**
     * 通过教师工号查询教师是否存在
     * @param teachId
     * @return
     * @throws Exception
     */
    @Override
    public Teacher findStudentByTeachId(String teachId) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeachIdEqualTo(teachId);
        List<Teacher> teaList = teacherMapper.selectByExample(teacherExample);
        if(teaList.size()>0){
            return teaList.get(0);
        }
        return null;
    }
    /**
     * 插入单挑学生信息
     * @param userInfo
     * @return
     * @throws UserException
     */
    @Override
    public boolean insertOneTeachItem(UserInfo userInfo) throws UserException {
        UserCheck<Teacher> userCheck = new TeacherCheck<>();
        Teacher teacher = userCheck.checkOneItem(userInfo,Teacher.class);
        try{
            int flag=0;
            if(findStudentByTeachId(teacher.getTeachId())==null){
                flag  = teacherMapper.insertSelective(teacher);
            }
            if(flag==1){
                System.out.println("插入成功");
                return true;
            }else{
                throw new UserException("学号错误");
            }

        }catch (UserException e){
            throw e;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 批量插入教师信息
     * @param mFile
     * @return
     * @throws Exception
     */
    @Override
    public ExcelUser<Teacher> insertTeachTable(MultipartFile mFile) throws Exception {
       /*
        单列教师信息插入
        UserCheck<Teacher> userCheck = new TeacherCheck<>();
        ExcelUser<Teacher> studentExcelUser = userCheck.checkManyItems(mFile,Teacher.class);
        List<Teacher> stuList = new ArrayList<>();
        for(Teacher teacher:studentExcelUser.getSuccessDeal()){
            if(findStudentByTeachId(teacher.getTeachId())==null){
                int flag = teacherMapper.insertSelective(teacher);
                if(flag==0){
                    stuList.add(teacher);
                }
            }else{
                stuList.add(teacher);
            }
        }
        studentExcelUser.setFailImport(stuList);
        return studentExcelUser;
        */

        UserCheck<Teacher> check= new TeacherCheck<>();
        ExcelUser<Teacher> teacherExcelUser = new ExcelUser<>();
        List<Teacher> stuList = new ArrayList<>();
        ProductService service =  check.sumbit(mFile.getInputStream());
        System.out.println(service.isEmpty()+","+service.isfinish());
        int successCount=0;

        while(service.isfinish() || !service.isEmpty()){
            System.out.println("开始获取数据");
            Teacher teacher= (Teacher) service.get();
            if(teacher!=null){
                successCount++;
                if(findStudentByTeachId(teacher.getTeachId())==null){
                    int flag = teacherMapper.insertSelective(teacher);
                    if(flag==0){
                        stuList.add(teacher);
                    }
                }else{
                    stuList.add(teacher);
                }
            }
        }
        System.out.println("插入已经结束");
        teacherExcelUser.setTotal(service.totoal());
        teacherExcelUser.setSuccessCount(successCount);
        teacherExcelUser.setFailImport(stuList);
        return teacherExcelUser;
    }
}
