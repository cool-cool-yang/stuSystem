package com.stuSystem.manager.listener;


import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
/*
获取项目的本地物理路径：用于配置资源的本机保存路径
 */
public class myListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        String webAppRootKey = event.getServletContext().getRealPath("/");
        int index = webAppRootKey.indexOf("target");
        if(index!=-1){
            webAppRootKey = webAppRootKey.substring(0,index);
        }
        System.setProperty("inf-root",webAppRootKey);
        System.out.println(System.getProperty("inf-root"));
    }
}
