package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.RequestReactivationaScholarshipUserInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialLdapReactivationScholarService;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialLdapReactivationScholarImpl implements the
 * InitialLdapReactivationScholar interface Contains the implementations of
 * method ldapReactivationScholar to consume the service (Reactivation
 * Scholar-ldap)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialLdapReactivationScholarServiceImpl implements
InitialLdapReactivationScholarService {
    private static final Logger LOG = Logger
    .getLogger(InitialLdapReactivationScholarServiceImpl.class);

    /**
     * 
     * Method ldapReactivationScholar. Contains the parameters for get the URL
     * for the service (Reactivation Scholar-ldap)
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param RequestReactivationaScholarshipUserInDTO
     *            beanrequestReactivationaScholarshipUserInDTO
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> ldapReactivationScholar(
            RequestReactivationaScholarshipUserInDTO beanrequestReactivationaScholarshipUserInDTO,
            HttpHeaders headers) {
        LOG.info("Inside ldapReactivationScholar");
        ClassPathXmlApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        try {
            context = SingletonApplicationContext.getInstance();
            LOG.info("headers-ldapReactivationScholar = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - ldapReactivationScholar");
//            finalUrl = AsoUtils.str_ldap_reactivation_scholar;
            LOG.info("EndUrl-ldapReactivationScholar = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanrequestReactivationaScholarshipUserInDTO,
                    headers);
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
