package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.GetStatusScholarshipUserInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialLdapGetStatusScholar;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialLdapGetStatusScholarImpl implements the
 * InitialLdapGetStatusScholar interface Contains the implementations of method
 * ldapGetStatusScholar to consume the service (Get Status Scholar-ldap)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialLdapGetStatusScholarImpl implements
InitialLdapGetStatusScholar {
    private static final Logger LOG = Logger
    .getLogger(InitialLdapGetStatusScholarImpl.class);
    private static final String CONFILE = "classpath*:/META-INF/spring/applicationContext.xml";

    /**
     * 
     * Method ldapGetStatusScholar. Contains the parameters for get the URL for
     * the service (Get Status Scholar-ldap)
     * 
     * @return a HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param GetStatusScholarshipUserInDTO
     *            beanGetStatusScholarshipUserInDTO
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> ldapGetStatusScholar(
            GetStatusScholarshipUserInDTO beanGetStatusScholarshipUserInDTO,
            HttpHeaders headers) {
        LOG.info("Inside ldapGetStatusScholar");
        ConfigurableApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        context = new ClassPathXmlApplicationContext(CONFILE);
        try {
            LOG.info("headers-ldapGetStatusScholar = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - ldapGetStatusScholar");
//            finalUrl = AsoUtils.str_ldap_getstatus_scholar;
            LOG.info("EndUrl-ldapGetStatusScholar = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanGetStatusScholarshipUserInDTO, headers);
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