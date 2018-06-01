package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.SendArchivingDocumentsInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialSendArchivingDocumentsService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialSendArchivingDocumentsServiceImpl implements the
 * InitialSendArchivingDocumentsService interface Contains the implementations
 * of method sendArchivingDocumentsClient to consume the service (Send
 * Document-Archiving)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialSendArchivingDocumentsServiceImpl implements
InitialSendArchivingDocumentsService {
    private static final Logger LOG = Logger
    .getLogger(InitialSendArchivingDocumentsServiceImpl.class);

    /**
     * 
     * Method sendArchivingDocumentsClient. Contains the parameters for get the
     * URL for the service (Send Document-Archiving)
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param SendArchivingDocumentsInDTO
     *            beanSendArchivingDocumentsInDTO
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> sendArchivingDocumentsClient(
            SendArchivingDocumentsInDTO beanSendArchivingDocumentsInDTO,
            HttpHeaders headers) {
        LOG.info("Inside sendArchivingDocumentsClient");
        String finalUrl = null;
        ClassPathXmlApplicationContext context = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-sendArchivingDocumentsClient = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - sendArchivingDocumentsClient");
//            finalUrl = AsoUtils.str_url_send_archiving_documents;
            LOG.info("EndUrl-sendArchivingDocumentsClient = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanSendArchivingDocumentsInDTO, headers);
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
