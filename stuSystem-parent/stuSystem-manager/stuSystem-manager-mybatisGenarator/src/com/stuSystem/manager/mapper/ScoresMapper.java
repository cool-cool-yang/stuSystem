package com.stuSystem.manager.mapper;

import com.stuSystem.manager.pojo.Scores;
import com.stuSystem.manager.pojo.ScoresExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScoresMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int countByExample(ScoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int deleteByExample(ScoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int deleteByPrimaryKey(Integer scoresId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int insert(Scores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int insertSelective(Scores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    List<Scores> selectByExample(ScoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    Scores selectByPrimaryKey(Integer scoresId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int updateByExampleSelective(@Param("record") Scores record, @Param("example") ScoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int updateByExample(@Param("record") Scores record, @Param("example") ScoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int updateByPrimaryKeySelective(Scores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table scores
     *
     * @mbggenerated Tue Jun 09 08:44:08 CST 2020
     */
    int updateByPrimaryKey(Scores record);
}