package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.DepositQueryInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialDepositQueryService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialDepositQueryClientServiceImpl implements the InitialDepositQueryClientService interface
 * Contains the implementations of method depositQuery to consume the service MGFBTH01
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialDepositQueryServiceImpl implements InitialDepositQueryService {
    private static final Logger LOG = Logger.getLogger(InitialDepositQueryServiceImpl.class);


    /**      
     *
     * Method depositQuery.
     * Contains the parameters for get the URL for the service MGFBTH01
     * @return HttpEntity<?> value as parameter, contains the headers and the body service response.
     *
     * @param DepositQueryInDTO beanScholarIn
     * @param HttpHeaders headers
     *
     */
    @Override
    public ResponseEntity<?> depositQuery(DepositQueryInDTO beanScholarIn,HttpHeaders headers) {LOG.info("Inside depositQuery");
    ClassPathXmlApplicationContext context=null;
    String finalUrl=null;
    ResponseEntity<?> response=null;
    ConsumerServiceImpl consumerServiceImpl=null;
    try{
        context=SingletonApplicationContext.getInstance();
        LOG.info("headers-depositQuery = "+headers);
        consumerServiceImpl = (ConsumerServiceImpl) context.getBean("ConsumerService");
        LOG.info("After autoinstance - depositQuery");
//        finalUrl = AsoUtils.str_url_deposit_query;
        LOG.info("EndUrl-depositQuery = "+finalUrl);
        response = consumerServiceImpl.restServiceConsume(HttpMethod.POST, finalUrl, beanScholarIn, headers);
    }catch(Exception exc){
        throw new FundacionBancomerExceptionHandler(exc);
    }finally{
        context.close();
        context=null;
        finalUrl=null;
        consumerServiceImpl=null;
    }
    return response;
    }
}
