package com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebback;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.bbva.arq.front.spring.mgbf.fundacionbancomerextranetwebdto.UploadImageFolioAndSendArchivingInDTO;

/**
 * Interface SendArchDocuUploImageBack. Contains
 * setUploadImageFolioAndSendArchiving method.
 * 
 * @author Diego Espinoza
 * @version 28 november 2017
 */
public interface SendArchDocuUploImageBack {
    /**
     * 
     * Method setUploadImageFolioAndSendArchiving
     * 
     * @return ResponseEntity<?> value as parameter.
     * 
     * @param UploadImageFolioAndSendArchivingInDTO
     *            beanUploadImageFolioAndSendArchivingInDTOIn
     * @param HttpServletRequest
     *            request
     * 
     */
    ResponseEntity<?> setUploadImageFolioAndSendArchiving(
            UploadImageFolioAndSendArchivingInDTO beanUploadImageFolioAndSendArchivingInDTOIn,
            HttpServletRequest request);

    /**
     * 
     * Method ChansgeJson
     * 
     * @return String value as parameter.
     * 
     * @param String
     *            json
     * 
     */
    String changeJsonSetUpImFoASedArch(String jsonpas);
}