package com.stuSystem.manager.custpojo;

import com.stuSystem.manager.pojo.*;

/**
 * 分数及及其关联信息的实体信息类
 */
public class CstmScores {

    private Integer scoresId;
    private Double scoresRes;
    private Teachcourse teachcourse;
    private Student student;
    private Teacher teacher;
    private CstmCourse cstmCourse;
    private Scores scores;
    public CstmScores(){}

    public CstmScores(Integer scoresId, Teachcourse teachcourse,
                      Student student, Teacher teacher, Double scoresRes) {
        this.scoresId = scoresId;
        this.teachcourse = teachcourse;
        this.student = student;
        this.teacher = teacher;
        this.scoresRes = scoresRes;
    }

    public CstmScores(Integer scoresId, Double scoresRes,
                      Teachcourse teachcourse, Student student, Teacher teacher, CstmCourse cstmCourse) {
        this.scoresId = scoresId;
        this.scoresRes = scoresRes;
        this.teachcourse = teachcourse;
        this.student = student;
        this.teacher = teacher;
        this.cstmCourse = cstmCourse;
    }

    public CstmCourse getCstmCourse() {
        return cstmCourse;
    }

    public void setCstmCourse(CstmCourse cstmCourse) {
        this.cstmCourse = cstmCourse;
    }

    public Integer getScoresId() {
        return scoresId;
    }

    public void setScoresId(Integer scoresId) {
        this.scoresId = scoresId;
    }

    public Teachcourse getTeachcourse() {
        return teachcourse;
    }

    public void setTeachcourse(Teachcourse teachcourse) {
        this.teachcourse = teachcourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getScoresRes() {
        return scoresRes;
    }

    public void setScoresRes(Double scoresRes) {
        this.scoresRes = scoresRes;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Double getScoreRes() {
        return scoresRes;
    }

    public void setScoreRes(Double scoreRes) {
        this.scoresRes = scoreRes;
    }

    public Scores getScores() {
        return scores;
    }

    public void setScores(Scores scores) {
        this.scores = scores;
    }
}
