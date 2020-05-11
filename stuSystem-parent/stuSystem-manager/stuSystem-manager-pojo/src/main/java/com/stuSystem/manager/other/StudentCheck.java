package com.stuSystem.manager.other;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
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
     * 学生检测类的具体实现
     * @param classInfo
     * @return
     */
    public boolean checkOther(String classInfo) {
        return classInfo.matches(maps.get("classPatter"));
    }

    @Override
    protected v createUser(UserInfo userInfo, Class<v> clazz) {

                return (v)UserFactory.createStudent(userInfo);
    }
    @Override
    public ExcelUser<v> checkManyItems(InputStream in, Class<v> clazz) throws UserException {

        try{
            Workbook wk = WorkbookFactory.create(in);   //自动获得表工作类
            Sheet sheet = wk.getSheetAt(0);  //获得第一个工作表
            List<v> stuList = new ArrayList<>();

            int count=0;
            for(Row row:sheet){
                count++;
                String userId = cellToStrval(row.getCell(0));
                if(!checkUserId(userId)){
                    System.out.println("学号错误");
                    continue;
                }

                String username = cellToStrval(row.getCell(1));
                if(!checkUsername(username)){
                    System.out.println("名字错误");
                    continue;
                }

                String userClass = cellToStrval(row.getCell(2));
                if(!checkOther(userClass)){
                    System.out.println("班级号错误");
                    continue;
                }

                String sex = cellToStrval(row.getCell(3));
                if(!checkUsersex(sex)){
                    System.out.println("性别错误");
                    continue;
                }

                String mobile = cellToStrval(row.getCell(4));
                if(!checkMobile(mobile)){
                    System.out.println("电话错误");
                    continue;
                }

                String email = cellToStrval(row.getCell(5));
                if(!checkEmail(email)){
                    System.out.println("邮箱错误");
                    continue;
                }
                if(!checkBirthday(row.getCell(6).getDateCellValue())){
                    System.out.println("生日错误");
                    continue;
                }else if(!checkenSch(row.getCell(7).getDateCellValue())){
                    System.out.println("入校时间错误");
                    continue;
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
                stuList.add((v)s);
            }
            ExcelUser<v> users = new ExcelUser<>();
            users.setTotal(count);
            users.setSuccessDeal(stuList);
            return users;
        }catch(InvalidFormatException e){
            throw new UserException("文件类型错误");
        }catch(IOException e){
            throw new UserException("文件打开错误");
        }
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
            System.out.println(stu);
        }catch(UserException e){
            e.printStackTrace();
        }
    }
}
