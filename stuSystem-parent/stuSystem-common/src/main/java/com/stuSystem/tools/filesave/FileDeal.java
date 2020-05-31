package com.stuSystem.tools.filesave;

import java.io.File;
import java.io.InputStream;

/*
文件处理接口
 */
public interface FileDeal {

    /**
     * 保存文件，成功返回保存后的路径，失败失败抛出异常
     * @param file
     * @return
     */
    String saveFile(File file)throws Exception;

    /**
     * 方法重载，参数为输入流
     * @param inputStream
     * @return
     * @throws Exception
     */

    /**
     * 获取文件,参数为文件路径，失败抛出异常
     * @param fileName
     * @return
     */
    File acceptFile(String fileName)throws Exception;
}
