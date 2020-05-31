package com.stuSystem.manager.listener;


import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

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
