package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback.AlertBack;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.AlertInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.conflict.MessageErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl.InitialAlertServiceImpl;


/**
 * Class AlertBackImpl implements the AlertBack interface Contains tho
 * implementations of the method alertArmed to consume the service MGBFTK01
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public class AlertBackImpl implements AlertBack {
    private static final Logger LOG = Logger.getLogger(AlertBackImpl.class);
    private boolean gtCad = false;
    public static final int VAL=200;

    /**
     * 
     * Method alertArmed. Contains the parameters for get the URL for the
     * service MGBFTK01
     * 
     * @return ResponseEntity<?> value as parameter, contains the headers and
     *         the body service response.
     * 
     * @param AlertInDTO
     *            beanAverageQueryIn
     * @param HttpServletRequest
     *            request
     * 
     */
    @Override
    public ResponseEntity<?> alertArmed(AlertInDTO beanAverageQueryIn,
            HttpServletRequest request) {
        LOG.info("Inside alertArmed");
        ClassPathXmlApplicationContext context = null;
        ResponseEntity<?> response = null;
        InitialAlertServiceImpl initialAlertServiceimpl = null;
        HttpHeaders headers = null;
        ErrorDTO errorDTO = null;
        MessageErrorDTO messageErrorDTO = null;
        try {
            context = SingletonApplicationContext.getInstance();
            initialAlertServiceimpl = (InitialAlertServiceImpl) context
            .getBean("initAlertService");
            headers = (HttpHeaders) context.getBean("httpHeaders");
            LOG.info("After Autoinstance - alertArmed");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("tsec", request.getSession().getAttribute("tsec")
                    .toString());
            LOG.info("headers-alertArmed " + headers);
            response = initialAlertServiceimpl.alertClient(beanAverageQueryIn,
                    headers);
            if (response.getStatusCode().value() != VAL) {
                LOG.info("Error consult - alertArmed ");
                messageErrorDTO = (MessageErrorDTO) response.getBody();

                if (messageErrorDTO.getErrorcode().equals("68")) {
                    LOG.info("TSEC  is timed out");
                    request.getSession().setAttribute("tsec", null);
                    response = this.alertArmed(beanAverageQueryIn, request);
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
            LOG.info("Entra en El Exception");
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context = null;
            headers = null;
            initialAlertServiceimpl = null;
            errorDTO = null;
            messageErrorDTO = null;
        }

        return response;
    }

}
