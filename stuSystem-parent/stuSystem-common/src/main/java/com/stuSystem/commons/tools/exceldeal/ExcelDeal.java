package com.stuSystem.commons.tools.exceldeal;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DecimalFormat;

public class ExcelDeal {

    /**
     * 将cell单元中的元素转换为字符串
     * @param cell
     * @return
     */
    public static String cellToStrval(Cell cell){
        if(cell==null ||cell.toString().equals("")){
            return "";
        }
        String val = "";
        switch (cell.getCellTypeEnum()){
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
}
