package com.stuSystem.manager.other;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Teacher;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherCheck<v extends Teacher> extends AbstractUserCheck<v> implements UserCheck<v> {
    /**
     *检查教师职称：
     * 主要是检查是否为空
     * @param userInfo
     * @return
     */
    @Override
    boolean checkOther(UserInfo userInfo) {
        return checkOther(userInfo.getTitle());
    }

    public boolean checkOther(String usertile) {
        if(usertile==null || usertile.trim().equals("")){
            return false;
        }
        return true;
    }

    /**
     * 创建教师实体
     * @param userInfo
     * @param clazz
     * @return
     */
    @Override
    protected v createUser(UserInfo userInfo, Class<v> clazz) {
        return (v)UserFactory.createTeacher(userInfo);
    }

    /**
     * 通过excel文件批量导入教师
     * @param in
     * @param clazz
     * @return
     * @throws UserException
     */
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
                    System.out.println("工号错误");
                    continue;
                }

                String username = cellToStrval(row.getCell(1));
                if(!checkUsername(username)){
                    System.out.println("名字错误");
                    continue;
                }

                String title = cellToStrval(row.getCell(2));
                if(!checkOther(title)){
                    System.out.println("职称错误");
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
                Teacher t =new Teacher();
                t.setTeachId(userId);
                t.setTeachName(username);
                t.setTeachTitle(title);
                t.setTeachSex(sex);
                t.setTeachMobile(mobile);
                t.setTeachEmail(email);
                t.setTeachBirthday(row.getCell(6).getDateCellValue());
                t.setTeachEnsch(row.getCell(7).getDateCellValue());
               t.setTeachEnsch(new Date());
                stuList.add((v)t);
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
}
