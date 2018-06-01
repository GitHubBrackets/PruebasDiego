package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialListStateService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialListStateServiceImpl implements the InitialListStateService
 * interface Contains the implementations of method listState to consume the
 * service (List State)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialListStateServiceImpl implements InitialListStateService {
    private static final Logger LOG = Logger
    .getLogger(InitialListStateServiceImpl.class);

    /**
     * 
     * Method listState. Contains the parameters for get the URL for the service
     * (List State), In this Method add value speials for build URL.
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> listState(HttpHeaders headers) {
        LOG.info("Inside listState");

        String finalUrl = null;
        ClassPathXmlApplicationContext context = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-listState = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstancia - listState");
//            finalUrl = AsoUtils.str_url_list_state;
            finalUrl = finalUrl
            + "?filter=(id=0)&operation=list&pageSize=120&start=0";
            LOG.info("EndUrl-listState = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.GET,
                    finalUrl, headers, "");
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context = null;
            finalUrl = null;
            consumerServiceImpl = null;
        }

        return response;

    }

}
