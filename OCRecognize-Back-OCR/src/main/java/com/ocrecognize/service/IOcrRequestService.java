package com.ocrecognize.service;

import java.io.IOException;

public interface IOcrRequestService {

    String getAllTextByUrlAndByOCRApiCompanyByGetRequest(String url, String ocrAPICompany);

    Boolean archiveDocumentByUrl(String url, String ocrAPICompany) throws IOException;

    String getFournisseurNameByOCRString(String textOcr);

    String getAllTextByFileAndByOCRApiCompanyByGetRequest(String file, String s);

    Boolean archiveDocumentByFile(String file);
}
