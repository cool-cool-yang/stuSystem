package com.stuSystem.manager.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuSystem.commons.tools.idGenerator.CourseType;
import com.stuSystem.manager.custpojo.*;
import com.stuSystem.manager.mapper.ScoresMapper;
import com.stuSystem.manager.mapper.TeachcourseMapper;
import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.ScoresExample;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.pojo.other.productService.ProductService;
import com.stuSystem.manager.pojo.other.usercheck.StudentCheck;
import com.stuSystem.manager.pojo.other.usercheck.StudentScoreCheck;
import com.stuSystem.manager.pojo.other.usercheck.UserCheck;
import com.stuSystem.manager.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("scoreServiceImp")
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private ScoresMapper scoresMapper;

    @Autowired
    private TeachcourseMapper teachcourseMapper;
    @Override
    public int insertManyItems(List<Scores> scoreList) {
        return scoresMapper.insertBatch(scoreList);
    }

    @Override
    public List<CstmScores> selectScoresByTcId(String tcId) {
        return scoresMapper.selectStuIdsByTcId(tcId);
    }

    @Override
    public boolean updateOneStuGrade(Scores scores) {

        /**
         * 再辞检验信息
         */
        if(scores.getScoresRes()<0 || scores.getScoresRes()>100){
            return false;
        }
        try{
            ScoresExample example = new ScoresExample();
            ScoresExample.Criteria criteria = example.createCriteria();
            /**
             * 以外键作为数据的更新条件
             */
            criteria.andScoresTcidEqualTo(scores.getScoresTcid());
            criteria.andScoresStuidEqualTo(scores.getScoresStuid());
            int flag = scoresMapper.updateByExampleSelective(scores,example);
            if(flag==1){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;   //处理所有数据库更新异常的情况。
        }
    }

    @Override
    public ExcelUser<Scores> updateStuGrasWithExcel(String scoresStuid, MultipartFile mFile) {

        //处理前准备：调用userCheck
        UserCheck<Scores> check= new StudentScoreCheck<>();
        ExcelUser<Scores> scoreExcelUser = new ExcelUser<>();
        List<Scores> scoresList = new ArrayList<>();    //数据库更新失败进行记录

        /*
        以生产者-消费者模式插入记录。
         */
        try{
            ProductService service =  check.sumbit(mFile.getInputStream());
            System.out.println(service.isEmpty()+","+service.isfinish());
            int successCount=0;
            while(service.isfinish() || !service.isEmpty()){
                System.out.println("开始获取数据");
                Scores sc= (Scores)service.get();
                if(sc!=null){
                    successCount++;
                    sc.setScoresTcid(scoresStuid);
                    if(!updateOneStuGrade(sc)){
                        scoresList.add(sc);
                    }
                }
            }
            System.out.println("插入已经结束");
            scoreExcelUser.setTotal(service.totoal());
            scoreExcelUser.setSuccessCount(successCount);
            scoreExcelUser.setFailImport(scoresList);
            return scoreExcelUser;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public PageInfo<CstmScores> findScoresPage(int start,String stuId) {
        PageHelper.startPage(start,10);
        ScoresExample example = new ScoresExample();
        example.setOrderByClause("scores_id ASC");
        ScoresExample.Criteria criteria = example.createCriteria();
        criteria.andScoresStuidEqualTo(stuId);
        List<Scores> scList =scoresMapper.selectByExample(example);
        List<CstmScores> cstList = new ArrayList<>();
        /*整合页面需要的信息*/
        for(Scores sc:scList){
            CstmTc cstmTc = teachcourseMapper.selectAssoCourseByTcId(sc.getScoresTcid());
            if(cstmTc!=null){
                CstmScores cstmScores = new CstmScores();
                //分数信息：包含课程编号和分数
                cstmScores.setScores(sc);
                //课程信息：包含课程名称和课程类型
                CstmCourse cstmCourse = new CstmCourse(cstmTc.getCourser(),
                        CourseType.values()[cstmTc.getCourser().getCourserType()-1]);
                cstmScores.setCstmCourse(cstmCourse);
                cstList.add(cstmScores);

            }

        }
        PageInfo<CstmScores> pageInfo = new PageInfo<>(cstList);
        return pageInfo;
    }

    @Override
    public CstmAnalyse findOneStuAnalyseByStuId(String stuId) {
        List<Double> resList = scoresMapper.selectStuTypesAveScoresByStuId(stuId);
        CstmAnalyse cstmAnalyse;

        /**
         * 数据填充依赖于当前项目的完成度。
         */
        if(resList!=null && resList.size()==4){
            /*
                对获得结果进行处理：
                （1）null置位0
                （2）inGame置位60
             */
            cstmAnalyse = new CstmAnalyse();
            Double theory = resList.get(0);
            theory= theory==null?Double.valueOf(0.0):theory;
            cstmAnalyse.setTheory(theory);

            Double mathCal = resList.get(1);
            mathCal = mathCal==null?Double.valueOf(0.0):mathCal;
            cstmAnalyse.setMathCal(mathCal);

            Double expe = resList.get(2);
            expe = expe==null?Double.valueOf(0.0):expe;
            cstmAnalyse.setExperiment(expe);

            Double other = resList.get(3);
            other = other==null?Double.valueOf(0.0):other;
            cstmAnalyse.setOther(other);
            cstmAnalyse.setInGame(Double.valueOf(60));
            Optional<Double> op1 = resList.stream().max((o1, o2)->(int)(o1.doubleValue()-o2.doubleValue()));
            if(op1!=null && op1.isPresent()){
                System.out.println("最大值："+op1.get());
                cstmAnalyse.setMax(op1.get());
            }else{
                cstmAnalyse.setMax(Double.valueOf(60));
            }
            Optional<Double> op2 = resList.stream().max((o1, o2)->(int)(o2.doubleValue()-o1.doubleValue()));
            if(op1!=null && op1.isPresent()){
                System.out.println("最小值："+op2.get());
                cstmAnalyse.setMin(op2.get());
            }else{
                cstmAnalyse.setMin(Double.valueOf(0));
            }
            Double av = resList.stream().mapToDouble((o) -> o.doubleValue()).sum()/5.0;
            cstmAnalyse.setAverage(av);
        }else{
            cstmAnalyse = new CstmAnalyse(Double.valueOf(60),Double.valueOf(60),Double.valueOf(60),Double.valueOf(60),
                                        Double.valueOf(60),Double.valueOf(60),Double.valueOf(60),Double.valueOf(60));
        }
        return cstmAnalyse;
    }
}
