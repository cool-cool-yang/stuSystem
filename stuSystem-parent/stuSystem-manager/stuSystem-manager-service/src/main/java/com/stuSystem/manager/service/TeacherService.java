package com.stuSystem.manager.service;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.other.myException.UserException;
import com.stuSystem.manager.pojo.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    /**
     * 通过给出的用户信息查询老师
     * @param userInfo
     * @return
     * @throws Exception
     */
    Teacher findTeacher(UserInfo userInfo)throws Exception;

    /**
     * 格局教师编号查询教师
     * @param teachId
     * @return
     * @throws Exception
     */
    Teacher findTeacherByTeachId(String teachId)throws Exception;

    /**
     * 插入一条教师记录
     * @param userInfo
     * @return
     * @throws UserException
     */
    boolean insertOneTeachItem(UserInfo userInfo)throws UserException;

    /**
     * 插入excel表
     * @param mFile
     * @return
     * @throws Exception
     */
    ExcelUser<Teacher> insertTeachTable(MultipartFile mFile)throws Exception;

    /**
     * 查询所有教师的编号和姓名
     * @return
     */
    List<Map<String,String>> findAllTeachers();

    /**
     * 根据给出的值更新教师信息
     * @param teacher
     * @return
     */
    boolean updateTeachInfo(Teacher teacher);
}
