package com.stuSystem.manager.service;

import com.stuSystem.manager.pojo.Teachcourse;

import java.util.List;
import java.util.Map;

public interface TeacherCourseService {



    /**
     * 插入一条开课信息
     * @param teachcourse
     * @return
     */
    String insetOneTeaCour(Teachcourse teachcourse)throws Exception;

    /**
     * 查询来时教授的所有授课
     * 返回所有授课ID和名称
     * @param teachId
     * @return
     */
    List<Map<String,String>> findTcByTeachId(String teachId);

    /**
     * 插入一条学生的入课信息
     * @param tcId
     * @param stuId
     * @return
     */
    boolean inserOneItemforTc(String tcId,String stuId);
}
