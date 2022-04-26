package com.curso.oyente;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class OyenteContexto implements ServletContextListener {

    public OyenteContexto() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("APLICACIÓN LEVANTADA!!!!");
    }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

}
