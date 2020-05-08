package com.stuSystem.manager.service;

import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.Student;

public interface StuService {
    Student findStudent(UserInfo userInfo)throws Exception;
}
