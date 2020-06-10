package com.stuSystem.manager.pojo.other.usercheck;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.other.myException.UserException;
import com.stuSystem.manager.pojo.other.productService.ProductService;
import com.stuSystem.manager.pojo.other.productService.ProductTask;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Usercheck的抽象类，实现通用检查
 * @param <T>
 */
public abstract class AbstractUserCheck<T> implements UserCheck<T>,ProductTask {
    public static Map<String,String> maps = new HashMap<>();
    public InputStream input;
    static{
        maps.put("IdPatter","^\\d{10}$");
        maps.put("phonePatter","^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
        maps.put("classPatter","^\\d{8}$");
        maps.put("emailPatter","^[_a-zA-Z\\d\\-\\.]+@[_a-zA-Z\\d\\-]+(\\.[_a-zA-Z\\d\\-]+)+$");
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
    /**
     *  空实现，需要子类进行实现
     * @param in
     * @param clazz
     * @return
     * @throws UserException
     */
    @Override
    public  ExcelUser<T> checkManyItems(InputStream in,Class<T> clazz) throws UserException {
        try{
            Workbook wk = WorkbookFactory.create(in);   //自动获得表工作类
            Sheet sheet = wk.getSheetAt(0);  //获得第一个工作表
            List<T> stuList = new ArrayList<>();

            int count=0;
            for(Row row:sheet){
                count++;
                T t  = CheckOneUser(row);
                stuList.add(t);
            }
            ExcelUser<T> users = new ExcelUser<>();
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
     * 内置生产者服务
     */
    public class ExcelService<T> implements ProductService<T> {
        //阻塞队列，会自动进行阻塞
        private volatile BlockingQueue<T> myQueue = new ArrayBlockingQueue<T>(10);
        private volatile boolean isFinish = true;
        private volatile int total=0;
        private InputStream input;  /*记录总共搜索到的学生*/
        public ExcelService(InputStream input) {
            this.input = input;
        }
        @Override
        public boolean isEmpty() {
           return myQueue.isEmpty();
        }

        @Override
        public boolean isFull() {
            if (myQueue.size() == 10) {
                return true;
            }
            return false;
        }
        @Override
        public boolean isfinish() {
            return isFinish;
        }
        @Override
        public int totoal() {
            return total;
        }

        @Override
        public T get() throws InterruptedException {
            //在队列头部最多等待30毫秒，否则放弃此次队列头元素获取。
            return myQueue.poll(30L, TimeUnit.MILLISECONDS);
        }

        /*
        可以改动成lamda表达式
         */
        @Override
        public boolean submit(ProductTask task) throws Exception {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        Workbook wk = WorkbookFactory.create(input);   //自动获得表工作类
                        Sheet sheet = wk.getSheetAt(0);  //获得第一个工作表
                        int count=0;
                        for (Row row : sheet) {
                            total++;
                            T t = (T) CheckOneUser(row);
                            System.out.println("搜索记录："+count++);
                            if (t != null) {
                                myQueue.put(t);
                            }
                        }
                    } catch (IOException | InvalidFormatException | InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        isFinish = false;
                        System.out.println("处理线程运行结束结束");
                    }
                }
            };
            thread.start(); /* 启动线程*/
            return true;
        }
    }
    public  ProductService<T> sumbit(InputStream input)throws Exception{
        this.input = input;
        ExcelService<T> service = new ExcelService<>(input);
        service.submit(this);
        return service;

    }
    abstract boolean checkOther(UserInfo userInfo);
    protected abstract T createUser(UserInfo userInfo, Class<T> clazz);
    abstract  T CheckOneUser(Row row);

}
