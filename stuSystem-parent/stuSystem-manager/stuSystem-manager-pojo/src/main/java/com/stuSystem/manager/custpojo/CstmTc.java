package com.stuSystem.manager.custpojo;

import com.stuSystem.manager.pojo.Courser;

/**
 * 课程与授课之间的关联实体
 */
public class CstmTc {
    private String tcId;
    private Courser courser;

    public CstmTc(){}

    public CstmTc(String tcId, Courser courser) {
        this.tcId = tcId;
        this.courser = courser;
    }

    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    public Courser getCourser() {
        return courser;
    }

    public void setCourser(Courser courser) {
        this.courser = courser;
    }

    @Override
    public String toString() {
        return "CstmTc{" +
                "tcId='" + tcId + '\'' +
                ", courser=" + courser +
                '}';
    }
}
