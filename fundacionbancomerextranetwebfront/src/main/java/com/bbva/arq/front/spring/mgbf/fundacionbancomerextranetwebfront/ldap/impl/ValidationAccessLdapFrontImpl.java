package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebfront.ldap.impl;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetutils.SingletonApplicationContext;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.ErrorDTO;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebexception.FundacionBancomerExceptionHandler;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebfront.ldap.ValidationAccessLdapFront;

/**
 * Class ValidationAccessLdapImpl implements the ValidationAccessLdap interface
 * Contains the implementations of the method validatePermissionsLdap to consume
 * the services with the App75 This class inplement APO to validate logged out
 * users.
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
@Aspect
public class ValidationAccessLdapFrontImpl implements ValidationAccessLdapFront {
    private String ivuser = "iv-user";
    private static final Logger LOG = Logger
    .getLogger(ValidationAccessLdapFrontImpl.class);

    /**
     * 
     * Method validatePermissionsLdap. Contains the parameters for get the URL
     * for the services with the App75
     * 
     * @return ResponseEntity<?> value as parameter, contains the headers and
     *         the body service response.
     * 
     * @param String
     *            json
     * @param HttpServletRequest
     *            request
     * 
     */
    @Pointcut("execution(* com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebfront.ldap.impl.*.*(..))")
    public void executeAroundValid() {
    }

    @Autowired
    @Around("executeAroundValid()")
    public ResponseEntity<?> validatePermissionsLdap(ProceedingJoinPoint pjp) {
        ClassPathXmlApplicationContext context = null;
        ErrorDTO errorDTO = null;
        ResponseEntity<?> response = null;
        ResponseEntity<?> responseNoAccess = null;

        try {
            context = SingletonApplicationContext.getInstance();
            ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes();
            LOG.info("IV-USER validatePermissionsLdap = "
                    + request.getRequest().getSession().getAttribute(ivuser));
            LOG.info("TSEC validatePermissions = "
                    + request.getRequest().getSession().getAttribute("tsec"));
            if ((request.getRequest().getSession().getAttribute(ivuser) != null
                    && !request.getRequest().getSession().getAttribute(ivuser)
                    .equals("null")
                    && !request.getRequest().getSession().getAttribute(ivuser)
                    .toString().equalsIgnoreCase("unauthenticated"))||request.getRequest().getSession().getAttribute("tsec") == null) {

                    LOG.info("iv-user without autorizacion");
                    errorDTO = (ErrorDTO) context.getBean("ErrorDTO");
                    errorDTO.setHttpStatusCode(String
                            .valueOf(HttpStatus.FORBIDDEN));
                    errorDTO.setCode(String.valueOf(errorDTO.hashCode()));
                    errorDTO.setMessage("Tu sesi�n ha caducado");
                    responseNoAccess = new ResponseEntity<ErrorDTO>(errorDTO,
                            null, HttpStatus.FORBIDDEN);
                
            }
            if (responseNoAccess == null) {
                response = (ResponseEntity<?>) pjp.proceed();
            } else {
                response = responseNoAccess;
            }
        } catch (Exception exc) {
            throw new FundacionBancomerExceptionHandler(exc);
        }finally {
             errorDTO = null;
             context = null;
         }

         return response;
    }

}
