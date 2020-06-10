package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.custpojo.CstmTc;
import com.stuSystem.manager.mapper.ScoresMapper;
import com.stuSystem.manager.mapper.TeachcourseMapper;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.TcParam;
import com.stuSystem.manager.pojo.Teachcourse;
import com.stuSystem.manager.service.TeacherCourseService;
import com.stuSystem.commons.tools.idGenerator.IDTools;
import org.apache.logging.log4j.core.net.TcpSocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("teacherCourseServiceImp")
public class TeacherCourseServiceImp implements TeacherCourseService {

    @Autowired
    private TeachcourseMapper teachcourseMapper;

    @Autowired
    private ScoresMapper scoresMapper;

    @Override
    public String insetOneTeaCour(Teachcourse teachcourse) throws Exception {
        String tcID = IDTools.getCourseID(16);
        while(teachcourseMapper.selectByPrimaryKey(tcID)!=null){
            tcID = IDTools.getCourseID(16);
        }
        teachcourse.setTcId(tcID);
        int flag = teachcourseMapper.insert(teachcourse);
        if(flag==1){
            return tcID;
        }
        return null;
    }

    @Override
    public List<Map<String, String>> findTcByTeachId(String teachId) {
        return teachcourseMapper.selectIdAndNameByTeachId(teachId);
    }

    @Override
    public boolean inserOneItemforTc(String tcId, String stuId) {
       /*
       向成绩表插入信息
        */
        Scores scores = new Scores();
        scores.setScoresTcid(tcId);
        scores.setScoresStuid(stuId);
        scores.setScoresIntime(new Date());

        /*
         *更新tc表信息
         */
        //确保成绩表插入成功
        if(scoresMapper.insert(scores)==1){
            TcParam tcParam = new TcParam(1,tcId);
            int flag = teachcourseMapper.updateStuCount(tcParam);
            return flag==1?true:false;
        }else{
            return false;
        }
    }
}
