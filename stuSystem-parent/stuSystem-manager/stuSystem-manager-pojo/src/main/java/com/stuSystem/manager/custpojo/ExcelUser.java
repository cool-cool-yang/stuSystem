package com.stuSystem.manager.custpojo;

import java.util.List;

/**
 * 用于传递导入表的信息
 * @param <T>
 */
public class ExcelUser<T> {
    private int total;  //总共扫描到的记录数量
    private int successCount;   //成功导入记录数量
    private List<T> successDeal;    //成功处理的记录
    private List<T> failImport;     //失败导入记录

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public List<T> getSuccessDeal() {
        return successDeal;
    }

    public void setSuccessDeal(List<T> successDeal) {
        this.successDeal = successDeal;
    }

    public List<T> getFailImport() {
        return failImport;
    }

    public void setFailImport(List<T> failImport) {
        this.failImport = failImport;
    }

    @Override
    public String toString() {
        return "ExcelUser{" +
                "total=" + total +
                ", successCount=" + successCount +
                ", successDeal=" + successDeal +
                ", failImport=" + failImport +
                '}';
    }
}
