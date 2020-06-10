package com.stuSystem.commons.tools.idGenerator;

/**
 * 课程的枚举类
 */
public enum CourseType {
    THEORY(1),MATH(2),PRACIICE(3),OTHER(4);
    int type;
    char title;
    String typeName;
    CourseType(int type){
        this.type = type;
        setTitle(type);
    }
    public void setTitle(int type) {
        switch (type){
            case 1:
                this.title='a';
                this.typeName="理论类";
                break;
            case 2:
                this.title='b';
                this.typeName="数学类";
                break;
            case 3:
                this.title='c';
                this.typeName="实验类";
                break;
            default:
                this.typeName="其他";
                this.title='d';
        }
    }
    public String getTypeName(){
        return this.typeName;
    }
}
