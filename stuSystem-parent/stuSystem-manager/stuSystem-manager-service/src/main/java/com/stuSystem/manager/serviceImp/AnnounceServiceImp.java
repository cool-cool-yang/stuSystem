package com.stuSystem.manager.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.mapper.AnnounceMapper;
import com.stuSystem.manager.pojo.Announce;
import com.stuSystem.manager.pojo.AnnounceExample;
import com.stuSystem.manager.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("announceServiceImp")
public class AnnounceServiceImp implements AnnounceService {

    @Autowired
    private AnnounceMapper announceMapper;

    /**
     * 插入一条公告记录
     * @param announce
     * @return
     */
    @Override
    public boolean InsertOneAnnounce(Announce announce) {
       announce.setAnnouIntime(new Date());
       int count = announceMapper.insert(announce);
       if(count==1){
           return true;
       }else{
           return false;
       }
    }

    @Override
    public PageInfo<Announce> findAnnouncePage(int start) {
        PageHelper.startPage(start,10);
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
        return pageInfo;
    }

    /**
     * 通过annouId查询一个Announce条目
     * @param annouId
     * @return
     */
    @Override
    public Announce findOneByAnnounceId(int annouId) {

        AnnounceExample example = new AnnounceExample();
        AnnounceExample.Criteria criteria = example.createCriteria();
        criteria.andAnnouIdEqualTo(annouId);
        List<Announce> lists = announceMapper.selectByExample(example);
        if(lists.size()>0){
            return lists.get(0);
        }
        return null;
    }
}
