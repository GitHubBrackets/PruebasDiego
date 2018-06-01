package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialListMunicipalityService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialListMunicipalityServiceImpl implements the
 * InitialListMunicipalityService interface Contains the implementations of
 * method listMunicipalityClient to consume the service (List Municipality)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialListMunicipalityServiceImpl implements
InitialListMunicipalityService {
    private static final Logger LOG = Logger
    .getLogger(InitialListMunicipalityServiceImpl.class);

    /**
     * 
     * Method listMunicipalityClient. Contains the parameters for get the URL
     * for the service (List Municipality), In this Method add value speials for
     * build URL.
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param int state
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> listMunicipalityClient(int state,
            HttpHeaders headers) {
        LOG.info("Inside listMunicipalityClient");
        String finalUrl = null;
        ClassPathXmlApplicationContext context = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-listMunicipalityClient = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - listMunicipalityClient");
//            finalUrl = AsoUtils.str_url_list_municipality;
            LOG.info("EndUrl-listMunicipalityClient = " + finalUrl);
            finalUrl = finalUrl.concat("?$filter={paramFilter}")
            .concat(String.valueOf(state))
            .concat(")&operation=BASICA&pageSize=600&start=0");
            String calCambChar = "(state==";
            response = consumerServiceImpl.restServiceConsume(HttpMethod.GET,
                    finalUrl, headers, calCambChar);
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
