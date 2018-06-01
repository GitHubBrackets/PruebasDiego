package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.GrantingTicketClientInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialClientGrantingTicketService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.GrantingTicketUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialClientGrantingTicketServiceImpl implements the
 * InitialClientGrantingTicketService interface Contains the implementations of
 * method consultGrantingTicketClient to consume the service GrantingTicket for
 * App74
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialClientGrantingTicketServiceImpl implements
InitialClientGrantingTicketService {

    private static final Logger LOG = Logger
    .getLogger(InitialClientGrantingTicketServiceImpl.class);

    /**
     * 
     * Method consultGrantingTicketClient. Contains the parameters for get the
     * URL for the service GrantingTicket for App74, also get the Object for the
     * request service.
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     */
    @Override
    public ResponseEntity<?> consultGrantingTicketClient() {
        LOG.info("Inside consultGrantingTicketClient");
        ResponseEntity<?> response = null;
        ClassPathXmlApplicationContext context = null;
        GrantingTicketUtils grantingTicketUtils = null;
        GrantingTicketClientInDTO grantingTicketClientInDTO = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        String urlGrantingTicket = null;
        try {
            context = SingletonApplicationContext.getInstance();
            grantingTicketUtils = (GrantingTicketUtils) context
            .getBean("gTUtils");
            grantingTicketClientInDTO = (GrantingTicketClientInDTO) context
            .getBean("gTClientDTO");
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - consultGrantingTicketClient");
//            urlGrantingTicket = AsoUtils.str_url_client_grantingticket;
            LOG.info("EndUrl-GrantingTicket = " + urlGrantingTicket);
            grantingTicketClientInDTO = grantingTicketUtils
            .getGrantingTicketClient();
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    urlGrantingTicket, grantingTicketClientInDTO, null);
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        } finally {
            context = null;
            grantingTicketUtils = null;
            grantingTicketClientInDTO = null;
            consumerServiceImpl = null;
            urlGrantingTicket = null;
        }
        return response;
    }

}
