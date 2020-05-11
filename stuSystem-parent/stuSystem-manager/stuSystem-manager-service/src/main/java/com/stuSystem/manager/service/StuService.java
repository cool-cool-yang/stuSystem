package com.stuSystem.manager.service;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StuService {
    Student findStudent(UserInfo userInfo)throws Exception;
    Student findStudentByStuId(String stuId)throws Exception;
    boolean insertOneStuItem(UserInfo userInfo)throws UserException;
    ExcelUser<Student> insertStuTable(MultipartFile mFile)throws Exception;
}
