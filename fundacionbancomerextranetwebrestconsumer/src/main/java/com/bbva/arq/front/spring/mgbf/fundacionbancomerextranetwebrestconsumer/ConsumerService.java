package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebrestconsumer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Interface ConsumerService. 
 * Contains two overloaded methods with different parameters,
 * Interface that contains the methods to consume the services.
 * 
 * @author Diego Espinoza
 * @version 24 november 2017
 */
public interface ConsumerService {

    /**
    *
    * Overloaded restServiceConsume method
    * @return HttpEntity<String> value as parameter contains the headers and the body service response.
    *
    * @param HttpMethod method
    * @param String url
    * @param Object object
    * @param HttpHeaders headers
    * 
    */
    ResponseEntity<?> restServiceConsume(HttpMethod method, String url, Object object, HttpHeaders headers);
    
    /**
    *
    * Overloaded restServiceConsume method
    * Returns HttpEntity<String> value as parameter, contains the headers and the body service response.
    *
    * @param HttpMethod method
    * @param String url
    * @param HttpHeaders headers
    * @param String calCambChar
    * 
    */
    ResponseEntity<?> restServiceConsume(HttpMethod method, String url, HttpHeaders headers, String calCambChar);
    
    /**
    *
    * Overloaded httpCEExceptionConsumer method
    * Returns void, contains the headers and the body service response.
    *
    * @param HttpClientErrorException httpcexc
    * @param ResponseEntity<?> responseout
    * 
    */
     void httpCEExceptionConsumer(HttpClientErrorException httpcexc, ResponseEntity<?> responseout);
    
    /**
    *
    * Overloaded httpSEExceptionConsumer method
    * Returns void, contains code catch error.
    *
    * @param HttpServerErrorException httpcexc
    * @param ResponseEntity<?> responseout
    * 
    */
     void httpSEExceptionConsumer(HttpServerErrorException httpcexc, ResponseEntity<?> responseout);
    
    /**
    *
    * Overloaded resourceAE method
    * Returns void, contains code catch error Time Out 504.
    *
    * @param (ResponseEntity<?> responseout
    * 
    */
     void resourceAE(ResponseEntity<?> responseout);
     
     /**
     *
     * Overloaded resourceNC method
     * Returns void, contains code catch error No context 204.
     *
     * @param (ResponseEntity<?> responseout
     * 
     */
     void resourceNC(ResponseEntity<?> responseout);
}

