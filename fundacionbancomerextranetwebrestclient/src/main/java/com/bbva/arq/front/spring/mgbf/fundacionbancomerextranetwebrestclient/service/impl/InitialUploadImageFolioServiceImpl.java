package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.UploadImageFolioInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialUploadImageFolioService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialUploadImageFolioServiceImpl implements the
 * InitialUploadImageFolioService interface Contains the uploadImageClient of
 * method uploadImageClient to consume the service MGBFTD01
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialUploadImageFolioServiceImpl implements
InitialUploadImageFolioService {
    private static final Logger LOG = Logger
    .getLogger(InitialUploadImageFolioServiceImpl.class);

    /**
     * 
     * Method uploadImageClient. Contains the parameters for get the URL for the
     * service MGBFTD01
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param UploadImageFolioInDTO
     *            beanUploadImageFolioIn
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> uploadImageClient(
            UploadImageFolioInDTO beanUploadImageFolioIn, HttpHeaders headers) {
        LOG.info("Inside uploadImageClient");
        ClassPathXmlApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-uploadImageClient = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After Autoinstance - uploadImageClient");
//            finalUrl = AsoUtils.str_url_upload_image_folio;
            LOG.info("EndUrl-uploadImageClient = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanUploadImageFolioIn, headers);
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
