package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.AlertInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialAlertService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialAlertServiceImpl implements the InitialAlertService interface
 * Contains the implementations of the method alertClient to consume the service MGBFTK01
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialAlertServiceImpl implements InitialAlertService{
    private static final Logger LOG = Logger.getLogger(InitialAlertServiceImpl.class);

    /**      
     *
     * Method alertClient.
     * Contains the parameters for get the URL for the service MGBFTK01
     * @return HttpEntity<?> value as parameter, contains the headers and the body service response.
     *
     * @param AlertInDTO valor
     * @param HttpHeaders headers
     *
     */
    @Override
    public ResponseEntity<?> alertClient(AlertInDTO valor, HttpHeaders headers) {LOG.info("Inside alertClient");
    ResponseEntity<?> response=null;
    ClassPathXmlApplicationContext context=null;
    String finalUrl=null;
    ConsumerServiceImpl consumerServiceImpl=null;
//    AsoUtils asoUtils=null;
    try{
        context=SingletonApplicationContext.getInstance();
        LOG.info("headers-alertClient = "+headers);
        consumerServiceImpl = (ConsumerServiceImpl) context.getBean("ConsumerService");
        LOG.info("After autoinstance - alertClient");
//        asoUtils = (AsoUtils) context.getBean("asoUtils");
//        finalUrl = asoUtils.str_url_alerts;
        LOG.info("EndUrl-alertClient = "+finalUrl);
        response = consumerServiceImpl.restServiceConsume(HttpMethod.POST, finalUrl, valor, headers);
    }catch(Exception exc){
        throw new FundacionBancomerExceptionHandler(exc);
    }finally{
        context = null;
        finalUrl=null;
        consumerServiceImpl=null;
    }
    return response;
    }

}
