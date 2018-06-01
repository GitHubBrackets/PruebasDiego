package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;

/**
 * Interface ValidGrantingTicket. Contains getGrantingTicketClient method.
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public interface ValidGrantingTicket {

    /**
     * 
     * Method getGrantingTicketNoClient
     * 
     * @return ResponseEntity<?> value as parameter.
     * 
     * @param ProceedingJoinPoint
     *            pjp
     * 
     */
    ResponseEntity<?> getGrantingTicketNoClient(ProceedingJoinPoint pjp)
            throws Throwable;

    /**
     * 
     * Method getGrantingTicketClient
     * 
     * @return ResponseEntity<?> value as parameter.
     * 
     * @param ProceedingJoinPoint
     *            pjp
     * 
     */
    ResponseEntity<?> getGrantingTicketClient(ProceedingJoinPoint pjp)
            throws Throwable;

}
