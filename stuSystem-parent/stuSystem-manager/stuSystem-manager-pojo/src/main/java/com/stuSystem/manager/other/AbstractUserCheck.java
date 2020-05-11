package com.stuSystem.manager.other;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import com.stuSystem.manager.pojo.Student;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Usercheck的抽象类，实现通用检查
 * @param <T>
 */
public abstract class AbstractUserCheck<T> implements UserCheck<T> {
    public static Map<String,String> maps = new HashMap<>();
    static{
        maps.put("IdPatter","^\\d{10}$");
        maps.put("phonePatter","^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
        maps.put("classPatter","^\\d{8}$");
        maps.put("emailPatter","^[_a-zA-Z\\d\\-\\.]+@[_a-zA-Z\\d\\-]+(\\.[_a-zA-Z\\d\\-]+)+$");
    }
    @Override
    public T checkOneItem(UserInfo userInfo,Class<T> clazz) throws UserException {
        /**
         * 逐步检查信息是否错误
         */
        if(!checkUserId(userInfo.getUserId())){
                throw new UserException("学号错误");
            }else if(!checkUsername(userInfo.getUsername())){
                throw new UserException("名字错误");
            }else if(!checkUsersex(userInfo.getUserSex())){
                throw new UserException("性别错误");
            }else if(!checkMobile(userInfo.getUserMobile())){
                throw new UserException("电话错误");
            }else if(!checkEmail(userInfo.getUserEmail())){
                throw new UserException("邮箱错误");
            }else if(!checkBirthday(userInfo.getUserBirthday())){
                throw new UserException("生日错误");
            }else if(!checkenSch(userInfo.getUserEnSch())){
                throw new UserException("入学时间错误");
            }else if(!checkOther(userInfo)){
                throw new UserException("其他错误");
            }
            //信息无误后，对请求的用户信息进行封装
            T t = createUser(userInfo,clazz);
        return t;
    }

    public boolean checkUserId(String userId){
        return userId.matches(maps.get("IdPatter"));
    }
    public boolean checkUsername(String username){
      username = username.trim();
      if(username.length()>=2 && username.length()<=20){
          return true;
      }
      return false;
    }
    public boolean checkUsersex(String userSex){
        if("男".equals(userSex) || "女".equals(userSex)){
            return true;
        }
        return false;
    }

    public boolean checkMobile(String mobile){
        return mobile.matches(maps.get("phonePatter"));
    }
    public boolean checkEmail(String userEmail){
       return userEmail.matches(maps.get("emailPatter"));
    }

    /**
     * 当前时间比<birthday时，返回false。
     * @param userBirthday
     * @return
     */
    public boolean checkBirthday(Date userBirthday){
        return userBirthday.before(new Date());
    }
    public boolean checkenSch(Date userEnSch){
        return true;
    }

    /**
     *  空实现，需要子类进行实现
     * @param in
     * @param clazz
     * @return
     * @throws UserException
     */
    @Override
    public  ExcelUser<T> checkManyItems(InputStream in,Class<T> clazz) throws UserException {

        return null;
    }

    /**
     * 重载方法，内部实现时调用了 checkManyItems(InputStream in,Class<T> clazz)
     * @param file
     * @param clazz
     * @return
     * @throws UserException
     */
    @Override
    public ExcelUser<T> checkManyItems(MultipartFile file, Class<T> clazz) throws UserException {

        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix;
        if(index!=-1){
            suffix =  fileName.substring(index+1);
            try {
                ExcelUser<T> users = checkManyItems(file.getInputStream(), clazz);
                return users;
            }catch(IOException e){
                e.printStackTrace();
                throw new UserException("文件处理失败");
            }
        }else{
            throw new UserException("文件类型错误");
        }
    }
    //根据后缀名创建exel的处理类
    public Workbook createBoolk(String suffix){
        if(".xls".equals(suffix)){
            return new HSSFWorkbook();
        }else if(".xlsx".equals(suffix)){
            return new XSSFWorkbook();
        }
        return null;
    }

    /**
     * excel元素返回为字符串
     * @param cell
     * @return
     */
    public String  cellToStrval(Cell cell){
        if(cell==null ||cell.toString().equals("")){
            return "";
        }
        String val = "";
        switch ( cell.getCellTypeEnum()){
            case NUMERIC:  //处理非日期的数值型
                if (!HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    val = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            case STRING:    //处理字符串型
                val = cell.getStringCellValue().trim();
                val = StringUtils.isEmpty(val)?"":val;
                break;
            case BOOLEAN: //处理布尔型
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
        }
        return val;
    }
    abstract boolean checkOther(UserInfo userInfo);
    protected abstract T createUser(UserInfo userInfo, Class<T> clazz);

}
