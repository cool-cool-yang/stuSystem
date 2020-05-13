package com.stuSystem.manager.other.usercheck;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.other.UserFactory;
import com.stuSystem.manager.pojo.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentCheck<v extends Student> extends AbstractUserCheck<v> implements UserCheck<v> {
    /**
     * 用于具体的扩展
     * @param userInfo
     * @return
     */
    @Override
    boolean checkOther(UserInfo userInfo) {
       String userClass = userInfo.getUserClass();
        return userClass.matches(maps.get("classPatter"));
    }

    /**
     * 学生特有属性的检查
     * @param classInfo
     * @return
     */
    public boolean checkOther(String classInfo) {
        return classInfo.matches(maps.get("classPatter"));
    }

    /**
     * 通过userinfo和实体类型获得实体
     * @param userInfo
     * @param clazz
     * @return
     */
    @Override
    protected v createUser(UserInfo userInfo, Class<v> clazz) {

                return (v) UserFactory.createStudent(userInfo);
    }
    /**
     * 检查单行row'
     * @param row
     * @return
     */
    @Override
    v CheckOneUser(Row row) {
        String userId = cellToStrval(row.getCell(0));
        if(!checkUserId(userId)){
            System.out.println("学号错误");
            return null;
        }

        String username = cellToStrval(row.getCell(1));
        if(!checkUsername(username)){
            System.out.println("名字错误");
            return null;
        }

        String userClass = cellToStrval(row.getCell(2));
        if(!checkOther(userClass)){
            System.out.println("班级号错误");
            return null;
        }

        String sex = cellToStrval(row.getCell(3));
        if(!checkUsersex(sex)){
            System.out.println("性别错误");
            return null;
        }

        String mobile = cellToStrval(row.getCell(4));
        if(!checkMobile(mobile)){
            System.out.println("电话错误");
            return null;
        }

        String email = cellToStrval(row.getCell(5));
        if(!checkEmail(email)){
            System.out.println("邮箱错误");
            return null;
        }
        if(!checkBirthday(row.getCell(6).getDateCellValue())){
            System.out.println("生日错误");
            return null;
        }else if(!checkenSch(row.getCell(7).getDateCellValue())){
            System.out.println("入校时间错误");
            return null;
        }
        Student s =new Student();
        s.setStuId(userId);
        s.setStuName(username);
        s.setStuClass(userClass);
        s.setStuSex(sex);
        s.setStuMobile(mobile);
        s.setStuEmail(email);
        s.setStuBirthday(row.getCell(6).getDateCellValue());
        s.setStuEnsch(row.getCell(7).getDateCellValue());
        s.setStuReglogin(new Date());
        return (v)s;
    }




    /**
     * 测试数据
     * @param args
     * @throws ParseException
     */
    public static void main(String args[]) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("2017211723");

        userInfo.setUsername("yrc");

        userInfo.setUserSex("男");
        userInfo.setUserMobile("13340250695");
        userInfo.setUserClass("12345678");
        userInfo.setUserEmail("1937282663@qq.com");
        userInfo.setUserBirthday(sdf.parse("2015-10-2"));
        userInfo.setUserEnSch(sdf.parse("2015-11-2"));
        System.out.println(userInfo);
        try{
            Student stu = (Student) new StudentCheck<Student>().checkOneItem(userInfo,Student.class);
            System.out.println(stu.getStuId());
        }catch(UserException e){
            e.printStackTrace();
        }
    }
}
