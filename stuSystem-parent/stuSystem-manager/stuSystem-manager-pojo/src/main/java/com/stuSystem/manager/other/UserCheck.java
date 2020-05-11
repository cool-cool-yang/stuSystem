package com.stuSystem.manager.other;

import com.stuSystem.manager.custpojo.ExcelUser;
import com.stuSystem.manager.custpojo.UserInfo;
import com.stuSystem.manager.myException.UserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface UserCheck<T> {
    /**
     * 检查一条信息
     * @param userInfo
     * @return
     * @throws UserException
     */
    T  checkOneItem(UserInfo userInfo,Class<T> clazz)throws UserException;

    /**
     * 通过输入流检查多条信息
     * @param in
     * @return
     * @throws UserException
     */
    ExcelUser<T> checkManyItems(InputStream in, Class<T> clazz)throws UserException;

    /**
     * 提供重载方法
     * @param mFile
     * @param clazz
     * @return
     * @throws UserException
     */
    ExcelUser<T>  checkManyItems(MultipartFile mFile, Class<T> clazz)throws UserException;
}
