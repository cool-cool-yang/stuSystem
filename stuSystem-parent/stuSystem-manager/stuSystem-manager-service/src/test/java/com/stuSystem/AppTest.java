package com.stuSystem;

import static org.junit.Assert.assertTrue;

import com.stuSystem.manager.mapper.CourserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /**
     * lambda表达式搜索队列的最大值个最小值
     */
    @Test
    public void streamMaxAndMin(){
        List<Double> list = new ArrayList<>();
        list.add(Double.valueOf(61));
        list.add(Double.valueOf(89));
        list.add(Double.valueOf(23));
        list.add(Double.valueOf(100));
        Optional<Double> op1 = list.stream().max((o1, o2)->(int)(o1.doubleValue()-o2.doubleValue()));
        if(op1!=null && op1.isPresent()){
            System.out.println("最大值："+op1.get());
        }
        Optional<Double> op2 = list.stream().max((o1, o2)->(int)(o2.doubleValue()-o1.doubleValue()));
        if(op1!=null && op1.isPresent()){
            System.out.println("最小值："+op2.get());
        }
    }
}
