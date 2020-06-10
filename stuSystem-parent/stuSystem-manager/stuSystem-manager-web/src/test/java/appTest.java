import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuSystem.commons.tools.idGenerator.CourseType;
import com.stuSystem.manager.custpojo.CstmScores;
import com.stuSystem.manager.custpojo.CstmTc;
import com.stuSystem.manager.mapper.AnnounceMapper;
import com.stuSystem.manager.mapper.CourserMapper;
import com.stuSystem.manager.mapper.ScoresMapper;
import com.stuSystem.manager.mapper.TeachcourseMapper;
import com.stuSystem.manager.pojo.*;
import com.stuSystem.manager.service.ScoreService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class appTest {

    ApplicationContext context;
    /*
    分页插件测试
     */
    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    }
    @Test
    public void PageHelperTest1()throws  Exception{
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        AnnounceMapper announceMapper = (AnnounceMapper) applicationContext.getBean(AnnounceMapper.class);
        PageHelper.startPage(1,3);
        AnnounceExample example = new AnnounceExample();
        example.setOrderByClause("annou_intime DESC");
        List<Announce> stuList = announceMapper.selectByExample(example);
        System.out.println(stuList.size());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Announce announce:stuList){
            System.out.println("标题："+announce.getAnnouName()+",发布时间："+sdf.format(announce.getAnnouIntime()));
        }
        PageInfo<Announce> pageInfo = new PageInfo<>(stuList);

        System.out.println("总的记录数量："+pageInfo.getTotal());
        System.out.println("总的页面数："+pageInfo.getPages());
        System.out.println("页面大小："+pageInfo.getPageSize());
    }

    /**
     * 测试自定义的全部查询（只获得固定字段）
     */
    @Test
    public void findAllTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        CourserMapper courserMapper = (CourserMapper) applicationContext.getBean(CourserMapper.class);
        List<Map<String,String>> list = courserMapper.selectAll();
        for(Map<String,String> map:list){
            System.out.println(map.get("courserId")+","+map.get("courserName"));
        }
    }

    /**
     * 测试根据前缀进行模糊查询
     */
    @Test
    public void selectCourseByPrenameTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        CourserMapper courserMapper = (CourserMapper) applicationContext.getBean(CourserMapper.class);
        List<Map<String,String>> list = courserMapper.selectCourseByPrename("黑龙");
        for(Map<String,String> map:list){
            System.out.println(map.get("courserId")+","+map.get("courserName"));
        }
    }

    /**
     * 测试mybatis的批量插入
     */
    @Test
    public void insetBatch(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ScoresMapper scoresMapper = applicationContext.getBean(ScoresMapper.class);
        List<Scores> scoresList = new ArrayList<>();
        Scores scores1 = new Scores();
        scores1.setScoresTcid("0679045621798425");
        scores1.setScoresStuid("1234567891");
        scoresList.add(scores1);

        Scores scores2 = new Scores();
        scores2.setScoresTcid("0679045621798425");
        scores2.setScoresStuid("1234567895");
        scoresList.add(scores2);
        scoresMapper.insertBatch(scoresList);

    }

    /**
     * teachCourse中的selectIdAndNameByTeachId方法测试
     */
    @Test
    public void selectIdAndNameByTeachIdTest(){
        TeachcourseMapper tcMapper = context.getBean(TeachcourseMapper.class);
        List<Map<String,String>> list = tcMapper.selectIdAndNameByTeachId("0000000002");
        for(Map<String,String> map:list){
            System.out.println(map.get("tcId")+","+map.get("tcCourser"));
        }
    }

    /**
     * updateStuCount测试
     */
    @Test
    public void updateStuCountTest(){
        TeachcourseMapper tcMapper = context.getBean(TeachcourseMapper.class);
        TcParam pojo = new TcParam(1,"1577187568914699");
        int count = tcMapper.updateStuCount(pojo);
        System.out.println(count);
    }

    /**
     * selectStuIdsByTcId测试：
     * 以List形式返回CstmScores信息
     * CstmScores：内部封装了完整的学生信息
     */
    @Test
    public void selectStuIdsByTcIdTest(){
        ScoresMapper scMapper = context.getBean(ScoresMapper.class);
        List<CstmScores> List = scMapper.selectStuIdsByTcId("1577187568914699");
        System.out.println("查询到的记录数："+List.size());
        if(List.size()>0){
            for(CstmScores cs:List){
                System.out.println(cs.getStudent().getStuName()+",分数："+cs.getScoreRes());
            }
        }
    }

    /**
     * 分数的分页查询
     */
    @Test
    public void findScoresPageTest(){
        ScoreService ss = context.getBean(ScoreService.class);
        PageInfo<CstmScores> pages = ss.findScoresPage(1,"1234567891");
        System.out.println("页面数量："+pages.getPages());
        System.out.println("总的条目数："+pages.getTotal());
        List<CstmScores> scoresList = pages.getList();

    }

    /**
     * selectAssoCourseByTcId测试
     */
    @Test
    public void selectAssoCourseByTcIdTest(){
        TeachcourseMapper mapper = context.getBean(TeachcourseMapper.class);
        CstmTc ctc = mapper.selectAssoCourseByTcId("1577187568914699");
        System.out.println(ctc.getTcId());
        System.out.println(ctc.getCourser().getCourserName());
        int type = ctc.getCourser().getCourserType();
        System.out.println(CourseType.values()[type-1].getTypeName());
    }

    /**
     * selectStuTypesAveScoresByStuId测试
     */
    @Test
    public void selectStuTypesAveScoresByStuIdTest(){
        ScoresMapper scoresMapper = context.getBean(ScoresMapper.class);
        List<Double> lists = scoresMapper.selectStuTypesAveScoresByStuId("1234567891");
        if(lists!=null && lists.size()>0){
            System.out.println("数据量："+lists.size());
            lists.stream().forEach(s-> System.out.println(s));
        }
    }



}
