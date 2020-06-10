package com.stuSystem.manager.custpojo;

import com.stuSystem.commons.tools.idGenerator.CourseType;
import com.stuSystem.manager.pojo.Courser;
import com.stuSystem.manager.pojo.other.UserFactory;
/*
课程实体的包装类
 */
public class CstmCourse {
    private Courser courser;
    private CourseType courseType;

    public CstmCourse(){}

    public CstmCourse(Courser courser, CourseType courseType) {
        this.courser = courser;
        this.courseType = courseType;
    }

    public Courser getCourser() {
        return courser;
    }

    public void setCourser(Courser courser) {
        this.courser = courser;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
