package com.stuSystem;

import static org.junit.Assert.assertTrue;

import com.stuSystem.commons.tools.idGenerator.CourseType;
import com.stuSystem.commons.tools.idGenerator.IDTools;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    /*
    ID测试
     */
    @Test
    public void test1() throws Exception {
        System.out.println(IDTools.getCourseID(16));
        System.out.println(IDTools.getCourseID(CourseType.MATH));
        System.out.println(IDTools.getCourseID(CourseType.THEORY,15));
    }
}
