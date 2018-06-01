package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.UploadQualificationsInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialUploadQualificationService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialUploadQualificationServiceImpl implements the
 * InitialUploadQualificationService interface Contains the implementations of
 * method uploadQualification to consume the service MGBFTC01
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialUploadQualificationServiceImpl implements
InitialUploadQualificationService {
    private static final Logger LOG = Logger
    .getLogger(InitialUploadQualificationServiceImpl.class);

    /**
     * 
     * Method uploadQualification. Contains the parameters for get the URL for
     * the service MGBFTC01
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param UploadQualificationsInDTO
     *            beanUploadQualificationnIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> uploadQualification(
            UploadQualificationsInDTO beanUploadQualificationnIn,
            HttpHeaders headers) {
        LOG.info("Inside uploadQualification");
        ClassPathXmlApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-uploadQualification = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - uploadQualification");
//            finalUrl = AsoUtils.str_url_upload_qualification;
            LOG.info("EndUrl-uploadQualification = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanUploadQualificationnIn, headers);
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            finalUrl = null;
            context = null;
            consumerServiceImpl = null;
        }
        return response;

    }
}
