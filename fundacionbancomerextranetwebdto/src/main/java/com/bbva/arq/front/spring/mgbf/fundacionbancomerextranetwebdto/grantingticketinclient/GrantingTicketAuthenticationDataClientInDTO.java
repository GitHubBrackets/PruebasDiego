package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.grantingticketinclient;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//Permite que Jackson ignore un objeto Vacio
@JsonIgnoreProperties(ignoreUnknown = true) 

/**
 * Class GrantingTicketAuthenticationDataClientInDTO
 * Contains the input values for the service GrantingTicket  App74
 * 
 * @author Diego Espinoza
 * @version 27 november 2017
 */
public class GrantingTicketAuthenticationDataClientInDTO {

    private String idAuthenticationData;
    private List<?> authenticationData;

    /**      
     *
     * Method getIdAuthenticationData.
     * Contains the get parameter for the service
     * @return String value as parameter.
     *
     */
    public String getIdAuthenticationData() {
        return idAuthenticationData;
    }

    /**      
     *
     * Method setIdAuthenticationData.
     * Contains the set parameter for the service
     * 
     * @param String idAuthenticationData
     * 
     */
    public void setIdAuthenticationData(String idAuthenticationData) {
        this.idAuthenticationData = idAuthenticationData;
    }

    /**      
     *
     * Method getAuthenticationData.
     * Contains the get parameter for the service
     * @return List<?> value as parameter.
     *
     */
    public List<?> getAuthenticationData() {
        return authenticationData;
    }

    /**      
     *
     * Method setAuthenticationData.
     * Contains the set parameter for the service
     * 
     * @param List<?> authenticationData
     * 
     */
    public void setAuthenticationData(List<?> authenticationData) {
        this.authenticationData = authenticationData;
    }

}
