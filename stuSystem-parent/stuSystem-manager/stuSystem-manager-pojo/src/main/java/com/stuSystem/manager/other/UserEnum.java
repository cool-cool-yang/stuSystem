package com.stuSystem.manager.other;

/**
 * 用户枚举类型
 */
public enum UserEnum {
    STUDENT(1),TEACHER(2),ADMIN(3);
    private int type;
    private UserEnum(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
}
