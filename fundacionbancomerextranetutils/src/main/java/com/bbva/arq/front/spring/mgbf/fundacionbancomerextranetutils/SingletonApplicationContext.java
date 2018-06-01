package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public final class SingletonApplicationContext{
    private static final Logger LOG = Logger.getLogger(SingletonApplicationContext.class);
    private static final String CONFILE= "classpath*:/META-INF/spring/applicationContext.xml";

    public static ClassPathXmlApplicationContext getInstance(){
        String appcontext="applicationcontext";
        ClassPathXmlApplicationContext context=null;
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        try{
            LOG.info("Into SingletonApplicationContextImpl.getInstance()");
            if(request.getRequest().getSession().getAttribute(appcontext)==null){
                LOG.info("new SingletonApplicationContextImpl.getInstance()");
                context =new ClassPathXmlApplicationContext(CONFILE);
                request.getRequest().getSession().setAttribute(appcontext, context);
            }

        }catch(Exception exc){
            request.getRequest().getSession().setAttribute(appcontext, null);
        }finally{
            context=null;
        }
        return (ClassPathXmlApplicationContext) request.getRequest().getSession().getAttribute(appcontext);
    }

    public static void closeContext(ClassPathXmlApplicationContext classPathXmlApplicationContext) {
        try{
            classPathXmlApplicationContext.close();
        }catch(Exception exc){
            classPathXmlApplicationContext.close();
        }


    }
    private SingletonApplicationContext() {
    }

}
