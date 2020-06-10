package com.stuSystem.manager.pojo.other.usercheck;

import com.stuSystem.commons.tools.exceldeal.ExcelDeal;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.pojo.Scores;
import org.apache.poi.ss.usermodel.Row;

public class StudentScoreCheck<v extends Scores> extends AbstractUserCheck<v> implements UserCheck<v> {
    @Override
    boolean checkOther(UserInfo userInfo) {
        return false;
    }

    @Override
    protected v createUser(UserInfo userInfo, Class<v> clazz) {
        return null;
    }

    @Override
    v CheckOneUser(Row row) {
        String userId = ExcelDeal.cellToStrval(row.getCell(0));
        if(!checkUserId(userId)){
            System.out.println("学号错误");
            return null;
        }
        Double res;
        try{
            res = row.getCell(1).getNumericCellValue();
            if(res<0 || res>100){
                System.out.println("学号错误");
                return null;
            }
        }catch (Exception e){
            System.out.println("不是数字");
            return null;
        }

        Scores sc = new Scores();
        sc.setScoresStuid(userId);
        sc.setScoresRes(res);
        return (v)sc;
    }
}
