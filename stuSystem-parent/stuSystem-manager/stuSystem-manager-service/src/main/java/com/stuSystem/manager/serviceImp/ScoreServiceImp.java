package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.custpojo.CstmScores;
import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.mapper.ScoresMapper;
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
@Service("scoreServiceImp")
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private ScoresMapper scoresMapper;
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
}
