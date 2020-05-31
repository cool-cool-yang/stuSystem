import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.mapper.AnnounceMapper;
import com.stuSystem.manager.mapper.StudentMapper;
import com.stuSystem.manager.pojo.Announce;
import com.stuSystem.manager.pojo.AnnounceExample;
import com.stuSystem.manager.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

public class PagehelpTest {

    @Test
    public void test()throws Exception{
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

}
