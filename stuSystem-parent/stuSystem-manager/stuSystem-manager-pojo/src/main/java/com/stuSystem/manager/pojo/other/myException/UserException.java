package com.stuSystem.manager.pojo.other.myException;

/**
 * 自定义用户异常
 */
public class UserException extends Exception {
  public UserException(){
      super();
  }
  public UserException(String msg){
      super(msg);
  }
}
