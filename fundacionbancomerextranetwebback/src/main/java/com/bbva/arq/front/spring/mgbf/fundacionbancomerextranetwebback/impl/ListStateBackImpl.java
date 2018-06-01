package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.ListStateBack;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.conflict.MessageErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl.InitialListStateServiceImpl;

/**
 * Class ListStateBackImpl implements the ListStateBack interface Contains the
 * implementations of the method listArchivingDocumentsArmed to consume the
 * service (List Document-Archiving)
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public class ListStateBackImpl implements ListStateBack {
    private static final Logger LOG = Logger.getLogger(ListStateBackImpl.class);
    private boolean gtCad = false;
    public static final int VAL = 200;

    /**
     * 
     * Method listArchivingDocumentsArmed. Contains the parameters for get the
     * URL for the service (List Document-Archiving)
     * 
     * @return ResponseEntity<?> value as parameter, contains the headers and
     *         the body service response.
     * 
     * @param HttpServletRequest
     *            request
     * 
     */
    @Override
    public ResponseEntity<?> listArchivingDocumentsArmed(
            HttpServletRequest request) {
        LOG.info("Inside listArchivingDocumentsArmed");

        ResponseEntity<?> response = null;
        ClassPathXmlApplicationContext context = null;
        HttpHeaders headers = null;
        ErrorDTO errorDTO = null;
        InitialListStateServiceImpl initialListStateServiceImpl = null;
        MessageErrorDTO messageErrorDTO = null;
        try {
            context = SingletonApplicationContext.getInstance();
            headers = (HttpHeaders) context.getBean("httpHeaders");
            initialListStateServiceImpl = (InitialListStateServiceImpl) context
            .getBean("listStateService");
            LOG.info("After Autoinstance - listArchivingDocumentsArmed");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("tsec", request.getSession().getAttribute("tsec")
                    .toString());
            LOG.info("headers-listArchivingDocumentsArmed = " + headers);
            response = initialListStateServiceImpl.listState(headers);
            if (response.getStatusCode().value() != VAL) {
                LOG.info("Error consult - listArchivingDocumentsArmed ");
                messageErrorDTO = (MessageErrorDTO) response.getBody();

                if (messageErrorDTO.getErrorcode().equals("68")) {
                    LOG.info("TSEC is timed out");
                    request.getSession().setAttribute("tsec", null);
                    response = this.listArchivingDocumentsArmed(request);
                    gtCad = true;
                }
                if (!gtCad) {
                    errorDTO = (ErrorDTO) context.getBean("ErrorDTO");
                    errorDTO.setHttpStatusCode(String.valueOf(response
                            .getStatusCode().value()));
                    errorDTO.setCode(messageErrorDTO.getErrorcode());
                    errorDTO.setMessage(messageErrorDTO.getErrormessage());
                    response = new ResponseEntity<ErrorDTO>(errorDTO,
                            response.getStatusCode());
                }
            }
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context = null;
            headers = null;
            errorDTO = null;
            messageErrorDTO = null;
        }
        return response;
    }

}
