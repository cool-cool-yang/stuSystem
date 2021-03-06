package com.stuSystem.manager.service;

import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.custpojo.CstmAnalyse;
import com.stuSystem.manager.custpojo.CstmScores;
import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.pojo.Scores;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScoreService {
    /**
     * 插入成绩集合
     * @param scoreList
     * @return
     */
    int insertManyItems(List<Scores> scoreList);

    /**
     * 查询某门授课的所有学生的分数信息
     * @param tcId
     * @return
     */
    List<CstmScores> selectScoresByTcId(String tcId);

    /**
     * 更新单条记录的信息
     * @param scores
     * @return
     */
    boolean updateOneStuGrade(Scores scores);

    /**
     * 批量更新分数信息
     * @param scoresStuid
     * @param mFile
     * @return
     */
    ExcelUser<Scores> updateStuGrasWithExcel(String scoresStuid, MultipartFile mFile);

    /**
     * 分页查询学生信息
     * 内部混合信息实体
     * @param start
     * @return
     */
    PageInfo<CstmScores> findScoresPage(int start,String stuId);


    CstmAnalyse findOneStuAnalyseByStuId(String stuId);

}
