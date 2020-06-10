package com.stuSystem.manager.pojo.other.usercheck;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.commons.tools.exceldeal.ExcelDeal;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 对只包含学生ID的excel进行处理
 */
public class StudentIdCheckTool {
    private static  String pattern = "^\\d{10}$";

    public static ExcelUser<String> dealExceltoList(MultipartFile multipartFile)throws Exception{
            if(multipartFile==null ||  multipartFile.isEmpty()|| multipartFile.getSize()<=0){
                throw new Exception("文件不存在或文件不能被处理");
            }

            int index = multipartFile.getOriginalFilename().lastIndexOf(".");
            System.out.println(multipartFile.getOriginalFilename());
            String suffix = multipartFile.getOriginalFilename().substring(index+1);
            System.out.println(suffix);
            if(!"xls".equals(suffix) && !"xlsx".equals(suffix)){
                throw new Exception("文件类型错误,不是excel表格");
            }
            return dealExceltoList(multipartFile.getInputStream());
    }

    /**
     * 使用流，需要在外部检查文件的合法性
     * @param inputStream
     * @return
     */
    public static ExcelUser<String> dealExceltoList(InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook wk = WorkbookFactory.create(inputStream);   //自动获得表工作类
        Sheet sheet = wk.getSheetAt(0);  //获得第一个工作表
        List<String> stuIdList = new ArrayList<>();
        ExcelUser<String> excelUser = new ExcelUser<>();
        int total=0;
        for(Row row:sheet){
            total++;
            //将excel中的数据转换为字符串
            String stuId = ExcelDeal.cellToStrval(row.getCell(0));
            if(stuId.matches(pattern)){
                stuIdList.add(stuId);
            }
        }
        excelUser.setTotal(total);
        excelUser.setSuccessDeal(stuIdList);
        return excelUser;
    }





}
