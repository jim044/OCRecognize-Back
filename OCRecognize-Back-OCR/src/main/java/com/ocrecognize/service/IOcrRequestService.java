package com.ocrecognize.service;

public interface IOcrRequestService {

    String getAllTextByUrlAndByOCRApiCompany(String url, String ocrAPICompany);

    String identifyWordWithMostOccurence(String textOcr);

    Boolean archiveDocumentByUrl(String url, String ocrAPICompany);

    Boolean verifyCompanyByNameWithApiCall(String companyName);
}
