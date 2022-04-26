package com.curso.cfg;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	
    	System.out.println("===============================");
    	System.out.println("= INICIALIZANDO LA APLICACION =");
    	System.out.println("===============================");
    	
    	WebApplicationContext appCtx = getContext();
    	
        servletContext.addListener(new ContextLoaderListener(appCtx));
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(appCtx));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/servicios/*");
       
    }
    
    //En este método inicalizamos y devolvemos el contenedor de Spring(Application context)
    private AnnotationConfigWebApplicationContext getContext() {
    	AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();
        appCtx.setConfigLocation("com.curso.cfg");
        return appCtx;
    }
    
}
