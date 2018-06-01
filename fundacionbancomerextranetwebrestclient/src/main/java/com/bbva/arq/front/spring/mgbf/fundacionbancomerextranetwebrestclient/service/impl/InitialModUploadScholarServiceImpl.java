package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ModUploadDataScholarInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialModUploadScholarService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialModUploadScholarClientServiceImpl implements the
 * InitialModUploadScholarClientService interface Contains the implementations
 * of method modUploadScholar to consume the service MGBFTB02
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialModUploadScholarServiceImpl implements
InitialModUploadScholarService {
    private static final Logger LOG = Logger
    .getLogger(InitialModUploadScholarServiceImpl.class);

    /**
     * 
     * Method modUploadScholar. Contains the parameters for get the URL for the
     * service MGBFTB02
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param ModUploadDataScholarInDTO
     *            beanModUploadDataScholarIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> modUploadScholar(
            ModUploadDataScholarInDTO beanModUploadDataScholarIn,
            HttpHeaders headers) {
        LOG.info("Inside modUploadScholar");
        String finalUrl = null;
        ClassPathXmlApplicationContext context = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-modUploadScholar = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - modUploadScholar");
//            finalUrl = AsoUtils.str_url_mod_upload_scholar;
            LOG.info("EndUrl-modUploadScholar = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanModUploadDataScholarIn, headers);
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