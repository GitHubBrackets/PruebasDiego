package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.DetailScholarInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialDetailScholarService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialDetailScholarServiceImpl implements the
 * InitialDetailScholarService interface Contains the implementations of method
 * detailScholarClient to consume the service MGBFTB03
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialDetailScholarServiceImpl implements
InitialDetailScholarService {
    private static final Logger LOG = Logger
    .getLogger(InitialDetailScholarServiceImpl.class);

    /**
     * 
     * Method detailScholarClient. Contains the parameters for get the URL for
     * the service MGBFTB03
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param DetailScholarInDTO
     *            beanDetailScholarIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> detailScholarClient(
            DetailScholarInDTO beanDetailScholarIn, HttpHeaders headers) {
        LOG.info("Inside detailScholarClient");
        ClassPathXmlApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - detailScholarClient");
//            finalUrl = AsoUtils.str_url_detail_scholar;
            LOG.info("EndUrl-detailScholarClient = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanDetailScholarIn, headers);
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context.close();
            context = null;
            finalUrl = null;
            consumerServiceImpl = null;
        }
        return response;
    }

}
