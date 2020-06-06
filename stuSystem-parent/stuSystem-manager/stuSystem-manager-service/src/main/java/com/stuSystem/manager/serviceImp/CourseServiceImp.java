package com.stuSystem.manager.serviceImp;

import com.stuSystem.manager.mapper.CourserMapper;
import com.stuSystem.manager.pojo.Courser;
import com.stuSystem.manager.pojo.CourserExample;
import com.stuSystem.manager.service.CourseService;
import com.stuSystem.commons.tools.idGenerator.CourseType;
import com.stuSystem.commons.tools.idGenerator.IDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 访问课程表
 */
@Service("courseServiceImp")
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourserMapper courserMapper;
    @Override
    public Courser findOneCourserById(String courseId) {
        CourserExample example = new CourserExample();
        CourserExample.Criteria criteria = example.createCriteria();
        criteria.andCourserIdEqualTo(courseId);

        List<Courser> courseList = courserMapper.selectByExample(example);
        if(courseList.size()>0){
            return courseList.get(0);
        }
        return null;
    }

    @Override
    public boolean inserOneCourser(Courser courser) throws Exception{
       CourseType courseType = CourseType.values()[courser.getCourserType()-1];
       String courseId = IDTools.getCourseID(courseType);
       //确保生成的课程ID在数据库中不存在
       while(findOneCourserById(courseId)!=null){
           courseId = IDTools.getCourseID(courseType);
       }
       //将课程信息补充完全。
       courser.setCourserId(courseId);
       //执行真正的插入
       int isInsert = courserMapper.insert(courser);
       if(isInsert==1){
           return true;
       }
       return false;
    }

    @Override
    public Courser findOneCourserByName(String courseName) {
        CourserExample example = new CourserExample();
        CourserExample.Criteria criteria = example.createCriteria();
        criteria.andCourserNameEqualTo(courseName);
        List<Courser> courseList = courserMapper.selectByExample(example);
        if(courseList.size()>0){
            return courseList.get(0);
        }
        return null;
    }


    @Override
    public List<Courser> findCoursersByPrename(String prename) {
        return null;
    }

    @Override
    public List<Map<String,String>> findAllCoursers() {
        /*
        selectByExample内置的查询条件是
         */
        return courserMapper.selectAll();
    }
}
