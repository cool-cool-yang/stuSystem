package com.stuSystem.manager.pojo;

public class TcParam {
    private Integer addCount;
    private String tcID;

    public TcParam(){}

    public TcParam(Integer addCount, String tcID) {
        this.addCount = addCount;
        this.tcID = tcID;
    }

    public int getAddCount() {
        return addCount;
    }

    public void setAddCount(Integer addCount) {
        this.addCount = addCount;
    }

    public String getTcID() {
        return tcID;
    }

    public void setTcID(String tcID) {
        this.tcID = tcID;
    }
}
