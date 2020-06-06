package com.stuSystem.manager.service;


import com.stuSystem.manager.pojo.Courser;

import java.util.List;
import java.util.Map;

public interface CourseService {
    /**
     * 通过ID查询该课程是否存在
     * @param courseId
     * @return
     */
    Courser findOneCourserById(String courseId);

    /**
     * 插入一条课程
     * @param courser
     * @return
     */
    boolean inserOneCourser(Courser courser)throws Exception;

    /**
     * 通过课程名称查询课程
     * @param courseName
     * @return
     */
    Courser findOneCourserByName(String courseName);

    /**
     * 根据名字前缀进行模糊匹配
     * @param prename
     * @return
     */
    List<Courser> findCoursersByPrename(String prename);

    /**
     * 查询所有的课程
     * @return
     */
    List<Map<String,String>> findAllCoursers();
}
