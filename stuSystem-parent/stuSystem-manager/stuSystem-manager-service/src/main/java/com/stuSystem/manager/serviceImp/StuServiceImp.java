package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.mapper.StudentMapper;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.pojo.StudentExample;
import com.stuSystem.manager.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuServiceImp")
public class StuServiceImp implements StuService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findStudent(UserInfo userInfo) throws Exception {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStuPwdEqualTo(userInfo.getPwd());
        criteria.andStuIdEqualTo(userInfo.getUsername());
        List<Student> stuLsit = studentMapper.selectByExample(studentExample);
        if(stuLsit.size()>0){
           Student stu =  stuLsit.get(0);
           stu.setStuOnline(1);
           return stu;
        }


        return null;
    }
}
