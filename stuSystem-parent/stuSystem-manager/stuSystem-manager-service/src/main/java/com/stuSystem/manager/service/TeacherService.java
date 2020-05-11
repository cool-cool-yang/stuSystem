package com.stuSystem.manager.service;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Teacher;
import org.springframework.web.multipart.MultipartFile;

public interface TeacherService {
    Teacher findTeacher(UserInfo userInfo)throws Exception;
    Teacher findStudentByTeachId(String teachId)throws Exception;
    boolean insertOneTeachItem(UserInfo userInfo)throws UserException;
    ExcelUser<Teacher> insertTeachTable(MultipartFile mFile)throws Exception;
}
