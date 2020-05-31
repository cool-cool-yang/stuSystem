package com.stuSystem.tools.filesave;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/*
将实现文件处理接口
作用：将文件保存在本地文件
 */
public class FileDealToLocal implements FileDeal {
    //初始化本地目录
    private static String rootPah;
    static{
        rootPah=System.getProperty("inf-root")+"src\\main\\webapp\\annoFiles\\";
        System.out.println(rootPah);
        File root = new File(rootPah);
        if( !root.exists()){
            root.mkdirs();
        }
    }

    /**
     * 保存文件到本地，传入参数为普通文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public String saveFile(File file) throws Exception {
       if(file.length()==0){
           throw new Exception("文件不能为空");
       }
       StringBuffer buffer = new StringBuffer(rootPah);
        String realName=getRealFileName(file.getName());
        buffer.append(realName);
        System.out.println(buffer.toString());
       File newFile = new File(buffer.toString());
       if(!newFile.exists()){
           newFile.createNewFile();
       }
       try{
           fileCopy(file,newFile);
           return realName;
        }catch(Exception e){
           throw new Exception("文件保存错误");
       }
    }
    public String saveFile(MultipartFile mFile) throws Exception {

        if(mFile==null || mFile.isEmpty()){
            throw new Exception("文件为空");
        }

        StringBuffer buffer = new StringBuffer(rootPah);
        String realName= getRealFileName(mFile.getOriginalFilename());
        buffer.append(realName);
        System.out.println(buffer.toString());
        File newFile = new File(buffer.toString());
        if(!newFile.exists()){
            newFile.createNewFile();
        }
        try{
            fileCopy(mFile.getInputStream(),new FileOutputStream(newFile));
            return realName;
        }catch(Exception e){
            throw new Exception("文件保存错误");
        }
    }
    /**
     *  获得本地文件
      * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public File acceptFile(String fileName) throws Exception {
        File f = new File(rootPah+"\\"+fileName);
        if(!f.exists()){
            throw new Exception("文件不存在");
        }
        return f;
    }

    /**
     * 实现文件复制
     * @param source
     * @param des
     * @throws Exception
     */
    public void fileCopy(File source,File des)throws Exception{
            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(des);
            byte[] b = new byte[1024];
            int len;
            while((len=inputStream.read(b))!=-1){
                outputStream.write(b,0,len);
            }
            try{
                if(inputStream!=null){
                    inputStream.close();;
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch(IOException e){
                throw new Exception("文件复制错误"+e.getMessage());
            }
    }

    /**
     * 流复制
     * @param input
     * @param out
     * @throws Exception
     */
    public void fileCopy(InputStream input,OutputStream out)throws Exception{
        byte[] b = new byte[1024];
        int len;
        while((len=input.read(b))!=-1){
            out.write(b,0,len);
        }
        try{
            if(input!=null){
                input.close();;
            }
            if(out!=null){
                out.close();
            }
        }catch(IOException e){
            throw new Exception("文件复制错误"+e.getMessage());
        }

    }
    private String getRealFileName(String filename){
        StringBuffer buf = new StringBuffer(String.valueOf(UUID.randomUUID()));
        int index = filename.indexOf(".");
        if(index!=-1){
            String suffix = filename.substring(index);
            buf.append(suffix);
        }
        return buf.toString();
    }

    //测试类
    public static void main(String args[]) throws Exception {
        FileDealToLocal f = new FileDealToLocal();
       File file = new File("F:\\temp\\b.docx");
        f.saveFile(file);

    }
}
