package com.supinfo.supcardealer.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.supcardealer.utils.PersistenceManager;


@WebListener
public class PersistenceAppListener implements ServletContextListener {

    public PersistenceAppListener() {
    }

    public void contextInitialized(ServletContextEvent arg0) {
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    	if(PersistenceManager.get() != null)
    		PersistenceManager.close();
    }
	
}