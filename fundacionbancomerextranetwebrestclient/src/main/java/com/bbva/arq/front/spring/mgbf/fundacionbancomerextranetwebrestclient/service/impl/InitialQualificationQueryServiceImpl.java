package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.QualificationsQueryInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialQualificationQueryService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialQualificationQueryServiceImpl implements the
 * InitialQualificationQueryService interface Contains the implementations of
 * method qualificationQuery to consume the service MGBFTC02
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialQualificationQueryServiceImpl implements
InitialQualificationQueryService {
    private static final Logger LOG = Logger
    .getLogger(InitialQualificationQueryServiceImpl.class);

    /**
     * 
     * Method qualificationQuery. Contains the parameters for get the URL for
     * the service MGBFTC02
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param QualificationsQueryInDTO
     *            beanQualificationsQueryIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> qualificationQuery(
            QualificationsQueryInDTO beanQualificationsQueryIn,
            HttpHeaders headers) {
        LOG.info("Inside qualificationQuery");
        ClassPathXmlApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-qualificationQuery = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - qualificationQuery");
//            finalUrl = AsoUtils.str_url_qualification_query;
            LOG.info("EndUrl-qualificationQuery = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanQualificationsQueryIn, headers);
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context = null;
            finalUrl = null;
            context = null;
            consumerServiceImpl = null;
        }
        return response;
    }

}
