package com.stuSystem.manager.service;

import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.Teacher;

public interface TeacherService {
    Teacher findTeacher(UserInfo userInfo)throws Exception;
}
