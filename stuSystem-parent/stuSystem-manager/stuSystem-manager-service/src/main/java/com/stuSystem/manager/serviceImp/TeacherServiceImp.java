package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.mapper.TeacherMapper;
import com.stuSystem.manager.pojo.Teacher;
import com.stuSystem.manager.pojo.TeacherExample;
import com.stuSystem.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
