package com.ocrecognize.service;

import com.ocrecognize.model.pojo.ResponseAPISirene;

import java.io.IOException;

public interface IOcrRequestService {

    String getAllTextByUrlAndByOCRApiCompany(String url, String ocrAPICompany);

    String identifyWordWithMostOccurence(String textOcr);

    Boolean archiveDocumentByUrl(String url, String ocrAPICompany) throws IOException;

    ResponseAPISirene verifyCompanyByNameWithApiCall(String companyName);
}
