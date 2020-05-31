package com.stuSystem.manager.service;

import com.github.pagehelper.PageInfo;
import com.stuSystem.manager.pojo.Announce;

public interface AnnounceService {
    boolean InsertOneAnnounce(Announce announce);
    PageInfo<Announce> findAnnouncePage(int start);
    Announce findOneByAnnounceId(int  annouId);

}
