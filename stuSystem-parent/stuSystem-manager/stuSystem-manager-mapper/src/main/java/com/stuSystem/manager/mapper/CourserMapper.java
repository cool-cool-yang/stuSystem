package com.stuSystem.manager.mapper;

import com.stuSystem.manager.pojo.Courser;
import com.stuSystem.manager.pojo.CourserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CourserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int countByExample(CourserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int deleteByExample(CourserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int deleteByPrimaryKey(String courserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int insert(Courser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int insertSelective(Courser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    List<Courser> selectByExample(CourserExample example);

    /**
     * 自定义查询所有的课程，并获得所有课程的Id和名字。
     * @return
     */
    List<Map<String,String>> selectAll();

    /**
     * 通过前缀名进行模糊查询
     * @param prename
     * @return
     */
    List<Map<String,String>> selectCourseByPrename(String prename);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    Courser selectByPrimaryKey(String courserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int updateByExampleSelective(@Param("record") Courser record, @Param("example") CourserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int updateByExample(@Param("record") Courser record, @Param("example") CourserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int updateByPrimaryKeySelective(Courser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table courser
     *
     * @mbggenerated Tue Jun 02 20:22:34 CST 2020
     */
    int updateByPrimaryKey(Courser record);
}