package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ValidationscholarInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialValidationScholarClientService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialValidationScholarClientServiceImpl implements the
 * InitialValidationScholarClientService interface Contains the implementations
 * of method validationScholar to consume the service MGBFTL01
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */

public class InitialValidationScholarClientServiceImpl implements
InitialValidationScholarClientService {
    private static final Logger LOG = Logger
    .getLogger(InitialValidationScholarClientServiceImpl.class);
    private static final String CONFILE = "classpath*:/META-INF/spring/applicationContext.xml";

    /**
     * 
     * Method validationScholar. Contains the parameters for get the URL for the
     * service MGBFTL01
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param ValidationscholarInDTO
     *            beanScholarIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> validationScholar(
            ValidationscholarInDTO beanScholarIn, HttpHeaders headers) {
        LOG.info("Inside validationScholar");
        ConfigurableApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        context = new ClassPathXmlApplicationContext(CONFILE);
        try {
            LOG.info("headers-validationScholar = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - validationScholar");
//            finalUrl = AsoUtils.str_url_validate_scholar;
            LOG.info("EndUrl-validationScholar = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanScholarIn, headers);
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context.close();
            finalUrl = null;
            consumerServiceImpl = null;
        }
        return response;
    }

}
