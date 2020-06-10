package com.stuSystem.manager.service;

import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.other.myException.UserException;
import com.stuSystem.manager.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

public interface StuService {
    /**
     * 根据部分信息查血学生
     * @param userInfo
     * @return
     * @throws Exception
     */
    Student findStudent(UserInfo userInfo)throws Exception;

    /**
     * 根据主键查询学生
     * @param stuId
     * @return
     * @throws Exception
     */
    Student findStudentByStuId(String stuId)throws Exception;

    /**
     * 导入一条学生信息
     * @param userInfo
     * @return
     * @throws UserException
     */
    boolean insertOneStuItem(UserInfo userInfo)throws UserException;

    /**
     * 通过Excel导入信息
     * @param mFile
     * @return
     * @throws Exception
     */
    ExcelUser<Student> insertStuTable(MultipartFile mFile)throws Exception;

    /**
     * 通过主键更新学生信息
     * @param student
     * @return
     */
    boolean updateStuInfo(Student student);
}
