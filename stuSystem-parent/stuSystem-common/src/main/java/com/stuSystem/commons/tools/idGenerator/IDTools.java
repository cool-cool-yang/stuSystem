package com.stuSystem.commons.tools.idGenerator;

import java.util.Random;

public class IDTools {

    private static Random random = new Random();

    /**
     * 生成指定长度的ID
     * @param length
     * @return
     * @throws Exception
     */
    public static String getCourseID(int length)throws  Exception{
       StringBuffer buffer = new StringBuffer(length);
       if(length<=0){
           throw new Exception("长度必须大于0");
       }
       for(int i=0;i<length;i++){
           buffer.append(random.nextInt(10));
       }
       return buffer.toString();
    }


    /**
     * 根据课程类型固定获得16位课程编号
     * ID开头:1-a，2-b，3-c，4-d。
     * 长度15位。
     * @param courseType
     * @return
     * @throws Exception
     */
    public static String getCourseID(CourseType courseType) throws Exception {
       return getCourseID(courseType,15);

    }


    /**
     * 据课程和类型创建课程ID
     * @param courseType
     * @param length
     * @return
     * @throws Exception
     */
    public static String getCourseID(CourseType courseType,int length) throws Exception {
        StringBuffer buffe = new StringBuffer(length+1);
        buffe.append(courseType.title);
        buffe.append(getCourseID(length));
        return buffe.toString();
    }

}
