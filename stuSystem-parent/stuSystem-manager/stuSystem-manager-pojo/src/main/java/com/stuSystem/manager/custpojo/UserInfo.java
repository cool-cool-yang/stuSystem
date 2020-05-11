package com.stuSystem.manager.custpojo;



import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户请求信息的封装类
 */
public class UserInfo {
    private int identity;
    private String username;
    private String pwd;
    private String  userId;
    private String userClass;
    private String userSex;
    private String userMobile;
    private String userEmail;
    private Date userBirthday;
    private Date userEnSch;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Date getUserEnSch() {
        return userEnSch;
    }

    public void setUserEnSch(Date userEnSch) {
        this.userEnSch = userEnSch;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "identity=" + identity +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", userId='" + userId + '\'' +
                ", userClass='" + userClass + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userBirthday=" + userBirthday +
                ", userEnSch=" + userEnSch +
                '}';
    }
}
