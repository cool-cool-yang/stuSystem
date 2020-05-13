package com.stuSystem.manager.other;

import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.other.UserEnum;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.pojo.Teacher;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.Date;

public class UserFactory {

    /**
     * 通过标识创建封装类
     * @param userEnum
     * @return
     */
    public static CstmUser createUser(UserEnum userEnum){
        switch(userEnum){
            case STUDENT:
                CstmUser<Student> cstmStu = new CstmUser<>();
                cstmStu.setUserType(userEnum.getType());
                return cstmStu;
            case TEACHER:
            case ADMIN:
                CstmUser<Teacher> cstmTeach = new CstmUser<>();
                cstmTeach.setUserType(userEnum.getType());
                return cstmTeach;

        }
        return null;
    }

    /**
     * 在工厂内部创建静态内部类，用于向页面传递用户实体信息
     * @param <T>
     */
    public static class CstmUser<T>{
        private int userType;
        private String username;
        private T user;

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public T getUser() {
            return user;
        }

        public void setUser(T user) {
            this.user = user;
        }
    }

    /**
     *通过userinfo实例化
     * @param userInfo
     * @return
     */
     public static Student createStudent(UserInfo userInfo){
         Student stu = new Student();
         stu.setStuId(userInfo.getUserId());
         stu.setStuName(userInfo.getUsername());
         stu.setStuSex(userInfo.getUserSex());
         stu.setStuClass(userInfo.getUserClass());
         stu.setStuEmail(userInfo.getUserEmail());
         stu.setStuMobile(userInfo.getUserMobile());
         stu.setStuBirthday(userInfo.getUserBirthday());
         stu.setStuEnsch(userInfo.getUserEnSch());
         stu.setStuReglogin(new Date());
         return stu;
     }
    public static Teacher createTeacher(UserInfo userInfo){
        Teacher teacher = new Teacher();
        teacher.setTeachId(userInfo.getUserId());
        teacher.setTeachName(userInfo.getUsername());
        teacher.setTeachSex(userInfo.getUserSex());
        teacher.setTeachTitle(userInfo.getTitle());

        teacher.setTeachEmail(userInfo.getUserEmail());
        teacher.setTeachMobile(userInfo.getUserMobile());
        teacher.setTeachBirthday(userInfo.getUserBirthday());
        teacher.setTeachEnsch(userInfo.getUserEnSch());
         teacher.setTeachReglogin(new Date());
        return teacher;
    }

    /**
     * 通过反射获得pojo类
     * 主要限于teacher和student
     * @param userInfo
     * @param clazz
     * @param <v>
     * @return
     */
    public static <v> v createUser(UserInfo userInfo,Class<v> clazz){
        try{
           v obj = clazz.newInstance();
            Field[] fs = clazz.getDeclaredFields();
            for(Field f:fs){
               if(f.getName().contains("Id")){
                   f.set(obj,userInfo.getUserId());
               }else if(f.getName().contains("Name")){
                   f.set(obj,userInfo.getUsername());
               }else if(f.getName().contains("Sex")){
                   f.set(obj,userInfo.getUserSex());
               }else if(f.getName().contains("Email")){
                   f.set(obj,userInfo.getUserEmail());
               }else if(f.getName().contains("Birthday")){
                   f.set(obj,userInfo.getUserBirthday());
               }else if(f.getName().contains("Mobile")){
                   f.set(obj,userInfo.getUserMobile());
               }else if(f.getName().contains("Class")){
                   f.set(obj,userInfo.getUserClass());
               }else if(f.getName().contains("Title")){
                   f.set(obj,userInfo.getTitle());
               }else if(f.getName().contains("Ensch")){
                   f.set(obj,userInfo.getUserEnSch());
               }else if(f.getName().contains("Reglogin")){
                   f.set(obj,new Date());
               }
            }
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }






}
