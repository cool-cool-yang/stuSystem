package com.stuSystem.manager.custpojo;

/**
 * 传递成绩分析的数据
 * 所有数据默认值为60
 */
public class CstmAnalyse {
    private Double experiment; //实验类
    private Double theory;  //理论
    private Double mathCal; //数学计算
    private Double other;   //其他

    private Double inGame ;  //参加比赛
    private Double max; //最大
    private Double min; //最小值
    private Double average;
    public CstmAnalyse(){}

    public CstmAnalyse(Double experiment, Double theory, Double mathCal, Double other, Double inGame, Double max, Double min) {
        this.experiment = experiment;
        this.theory = theory;
        this.mathCal = mathCal;
        this.other = other;
        this.inGame = inGame;
        this.max = max;
        this.min = min;
    }

    public CstmAnalyse(Double experiment, Double theory, Double mathCal, Double other,
                       Double inGame, Double max, Double min, Double average) {
        this.experiment = experiment;
        this.theory = theory;
        this.mathCal = mathCal;
        this.other = other;
        this.inGame = inGame;
        this.max = max;
        this.min = min;
        this.average = average;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getExperiment() {
        return experiment;
    }

    public void setExperiment(Double experiment) {
        this.experiment = experiment;
    }

    public Double getTheory() {
        return theory;
    }

    public void setTheory(Double theory) {
        this.theory = theory;
    }

    public Double getMathCal() {
        return mathCal;
    }

    public void setMathCal(Double mathCal) {
        this.mathCal = mathCal;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getInGame() {
        return inGame;
    }

    public void setInGame(Double inGame) {
        this.inGame = inGame;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "CstmAnalyse{" +
                "experiment=" + experiment +
                ", theory=" + theory +
                ", mathCal=" + mathCal +
                ", other=" + other +
                ", inGame=" + inGame +
                ", max=" + max +
                ", min=" + min +
                '}';
    }
}
