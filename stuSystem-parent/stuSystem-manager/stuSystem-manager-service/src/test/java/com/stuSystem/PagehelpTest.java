package com.stuSystem;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.mapper.StudentMapper;
import com.stuSystem.manager.pojo.Student;
import com.stuSystem.manager.pojo.StudentExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PagehelpTest {

    @Test
    public void test()throws Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        StudentMapper studentMapper = (StudentMapper)applicationContext.getBean(StudentMapper.class);
        PageHelper.startPage(1,3);
        StudentExample example = new StudentExample();

        List<Student> stuList = studentMapper.selectByExample(example);
        PageInfo<Student> pageInfo = new PageInfo<>(stuList);
        System.out.println("总的记录数量："+pageInfo.getTotal());
        System.out.println("总的页面数："+pageInfo.getPages());
        System.out.println("页面大小："+pageInfo.getPageSize());
    }

}
