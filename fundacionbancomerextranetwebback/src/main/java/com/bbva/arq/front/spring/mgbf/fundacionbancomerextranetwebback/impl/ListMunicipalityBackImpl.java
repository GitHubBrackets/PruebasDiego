package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.ListMunicipalityBack;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.conflict.MessageErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl.InitialListMunicipalityServiceImpl;

/**
 * Class ListMunicipalityBackImpl implements the ListMunicipalityBack interface
 * Contains the implementations of the method listMunicipalityArmed to consume
 * the service (List municipality)
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public class ListMunicipalityBackImpl implements ListMunicipalityBack {
    private static final Logger LOG = Logger
    .getLogger(ListMunicipalityBackImpl.class);
    private boolean gtCad = false;
    public static final int VAL = 200;

    /**
     * 
     * Method listMunicipalityArmed. Contains the parameters for get the URL for
     * the service (List municipality)
     * 
     * @return ResponseEntity<?> value as parameter, contains the headers and
     *         the body service response.
     * 
     *         int state
     * @param HttpServletRequest
     *            request
     * 
     */
    @Override
    public ResponseEntity<?> listMunicipalityArmed(int state,
            HttpServletRequest request) {
        LOG.info("Inside listMunicipalityArmed");
        ResponseEntity<?> response = null;
        ClassPathXmlApplicationContext context = null;
        HttpHeaders headers = null;
        ErrorDTO errorDTO = null;
        MessageErrorDTO messageErrorDTO = null;
        InitialListMunicipalityServiceImpl initialListMunicipalyServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            headers = (HttpHeaders) context.getBean("httpHeaders");
            initialListMunicipalyServiceImpl = (InitialListMunicipalityServiceImpl) context
            .getBean("listMunicipalService");
            LOG.info("After Autoinstance - listMunicipalityArmed");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("tsec", request.getSession().getAttribute("tsec")
                    .toString());
            LOG.info("headers-listMunicipalityArmed = " + headers);
            response = initialListMunicipalyServiceImpl.listMunicipalityClient(
                    state, headers);
            if (response.getStatusCode().value() != VAL) {
                LOG.info("Error consult - listMunicipalityArmed ");
                messageErrorDTO = (MessageErrorDTO) response.getBody();

                if (messageErrorDTO.getErrorcode().equals("68")) {
                    LOG.info("TSEC is timed out");
                    request.getSession().setAttribute("tsec", null);
                    response = this.listMunicipalityArmed(state, request);
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
