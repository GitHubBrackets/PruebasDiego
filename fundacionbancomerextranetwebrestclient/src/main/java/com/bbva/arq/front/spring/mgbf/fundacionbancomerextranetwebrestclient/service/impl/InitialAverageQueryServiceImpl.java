package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.AveragesQueryInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialAverageQueryService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialAverageQueryServiceImpl implements the InitialAverageQueryService interface
 * Contains the implementations of method averageQueryClient to consume the service MGBFTC03
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialAverageQueryServiceImpl implements InitialAverageQueryService {
    private static final Logger LOG = Logger.getLogger(InitialAverageQueryServiceImpl.class);


    /**      
     *
     * Method averageQueryClient.
     * Contains the parameters for get the URL for the service MGBFTC03
     * @return HttpEntity<?> value as parameter, contains the headers and the body service response.
     *
     * @param AveragesQueryInDTO beanAverageQueryIn
     * @param HttpHeaders headers
     *
     */
    @Override
    public ResponseEntity<?> averageQueryClient(AveragesQueryInDTO beanAverageQueryIn, HttpHeaders headers) {LOG.info("Inside averageQueryClient");
    ResponseEntity<?> response=null;
    ClassPathXmlApplicationContext context=null;
    String finalUrl=null;
    ConsumerServiceImpl consumerServiceImpl=null;
    try{
        context=SingletonApplicationContext.getInstance();
        LOG.info("headers-averageQueryClient = "+headers);
        consumerServiceImpl = (ConsumerServiceImpl) context.getBean("ConsumerService");
        LOG.info("After autoinstance - averageQueryClient");
//        finalUrl = AsoUtils.str_url_averages_query;
        LOG.info("EndUrl-averageQueryClient = "+finalUrl);
        response = consumerServiceImpl.restServiceConsume(HttpMethod.POST, finalUrl, beanAverageQueryIn, headers);
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
