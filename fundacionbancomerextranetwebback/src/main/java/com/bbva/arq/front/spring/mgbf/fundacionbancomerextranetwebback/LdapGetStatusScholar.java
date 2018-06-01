package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.GetStatusScholarshipUserInDTO;

/**
 * Interface LdapGetStatusScholar. 
 * Contains ldapGetStatusArmed method.
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public interface LdapGetStatusScholar {

    /**
     *
     * Method ldapGetStatusArmed
     * @return ResponseEntity<?> value as parameter.
     *
     * @param GetStatusScholarshipUserInDTO beanGetStatusScholarshipUserInDTO
     * @param HttpServletRequest request
     *
     */
    ResponseEntity<?> ldapGetStatusArmed(GetStatusScholarshipUserInDTO beanGetStatusScholarshipUserInDTO, HttpServletRequest request);

}
