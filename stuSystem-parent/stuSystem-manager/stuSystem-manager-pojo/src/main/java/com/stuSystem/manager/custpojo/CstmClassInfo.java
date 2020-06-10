package com.stuSystem.manager.custpojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 开课信息的pojo
 */
public class CstmClassInfo {
    private  String courseId;
    private  String teachId;
    private MultipartFile mFile;

    public CstmClassInfo(){

    }

    public CstmClassInfo(String courseId, String teachId, MultipartFile mFile) {
        this.courseId = courseId;
        this.teachId = teachId;
        this.mFile = mFile;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeachId() {
        return teachId;
    }

    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    public MultipartFile getmFile() {
        return mFile;
    }

    public void setmFile(MultipartFile mFile) {
        this.mFile = mFile;
    }

    @Override
    public String toString() {
        return "CstmClassInfo{" +
                "courseId='" + courseId + '\'' +
                ", teachId='" + teachId + '\'' +
                ", mFile=" + mFile +
                '}';
    }
}
