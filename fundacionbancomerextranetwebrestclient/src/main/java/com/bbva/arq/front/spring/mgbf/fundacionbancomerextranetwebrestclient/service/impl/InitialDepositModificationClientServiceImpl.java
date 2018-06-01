package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.DepositModificationInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialDepositModificationClientService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialDepositModificationClientServiceImpl implements the
 * InitialDepositModificationClientService interface Contains the
 * implementations of method depositModification to consume the service MGBFTH02
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialDepositModificationClientServiceImpl implements
InitialDepositModificationClientService {
    private static final Logger LOG = Logger
    .getLogger(InitialDepositModificationClientServiceImpl.class);
    private static final String CONFILE = "classpath*:/META-INF/spring/applicationContext.xml";

    /**
     * 
     * Method depositModification. Contains the parameters for get the URL for
     * the service MGBFTH02
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param DepositModificationInDTO
     *            beanDepositModificationIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> depositModification(
            DepositModificationInDTO beanDepositModificationIn,
            HttpHeaders headers) {
        LOG.info("Inside depositModification");
        ConfigurableApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        context = new ClassPathXmlApplicationContext(CONFILE);
        try {
            LOG.info("headers-depositModification = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - depositModification");
//            finalUrl = AsoUtils.str_url_modif_deposit;
            LOG.info("EndUrl-depositModification = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanDepositModificationIn, headers);
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
