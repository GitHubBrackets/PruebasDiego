package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.CreateScholarshipUserInDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.InitialLdapCreateScholar;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestclient.service.utils.AsoUtils;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer.impl.ConsumerServiceImpl;

/**
 * Class InitialLdapCreateScholarImpl implements the InitialLdapCreateScholar
 * interface Contains the implementations of method ldapCreateScholar to consume
 * the service (Create Scholar-ldap)
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class InitialLdapCreateScholarImpl implements InitialLdapCreateScholar {
    private static final Logger LOG = Logger
    .getLogger(InitialLdapCreateScholarImpl.class);
    private static final String CONFILE = "classpath*:/META-INF/spring/applicationContext.xml";

    /**
     * 
     * Method ldapCreateScholar. Contains the parameters for get the URL for the
     * service (Create Scholar-ldap)
     * 
     * @return HttpEntity<?> value as parameter, contains the headers and the
     *         body service response.
     * 
     * @param CreateScholarshipUserInDTO
     *            beanCreateScholarshipUserInDTO
     * @param HttpHeaders
     *            headers
     * 
     */
    @Override
    public ResponseEntity<?> ldapCreateScholar(
            CreateScholarshipUserInDTO beanCreateScholarshipUserInDTO,
            HttpHeaders headers) {
        LOG.info("Inside ldapCreateScholar");
        ConfigurableApplicationContext context = null;
        String finalUrl = null;
        ResponseEntity<?> response = null;
        ConsumerServiceImpl consumerServiceImpl = null;
        context = new ClassPathXmlApplicationContext(CONFILE);
        try {
            LOG.info("headers-ldapCreateScholar = " + headers);
            consumerServiceImpl = (ConsumerServiceImpl) context
            .getBean("ConsumerService");
            LOG.info("After autoinstance - ldapCreateScholar");
//            finalUrl = AsoUtils.str_ldap_create_scholar;
            LOG.info("EndUrl-ldapCreateScholar = " + finalUrl);
            response = consumerServiceImpl.restServiceConsume(HttpMethod.POST,
                    finalUrl, beanCreateScholarshipUserInDTO, headers);
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
