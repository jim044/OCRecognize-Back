package com.ocrecognize.service.impl;

import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.model.pojo.ResponseAPISirene;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IOcrRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class OcrRequestService implements IOcrRequestService {

    @Autowired
    private OCRProperties ocrProperties;

    @Autowired
    @Qualifier("ClientAuthentificationRestemplate")
    RestTemplate restTemplate;

    @Override
    public Boolean archiveDocumentByUrl(String url, String ocrAPICompany) {
        String getAllTextByUrlAndByOCRApiCompany = getAllTextByUrlAndByOCRApiCompany(url, ocrAPICompany);

        if(getAllTextByUrlAndByOCRApiCompany != null){
            String presumeCompanyName = identifyWordWithMostOccurence(getAllTextByUrlAndByOCRApiCompany);
            if(verifyCompanyByNameWithApiCall(presumeCompanyName)){
                String folderUri = createFolderByCompanyName(presumeCompanyName);
            }
        }

        return false;
    }

    @Override
    public String getAllTextByUrlAndByOCRApiCompany(String url, String ocrAPICompany) {
        ResponseOCRSpaceByUrl resultResponse = restTemplate.getForObject(ocrProperties.getUrl().get(ocrAPICompany).replace("{apiKey}", ocrProperties.getApiKey().get(ocrAPICompany)) + "&url=" + url, ResponseOCRSpaceByUrl.class);
        return resultResponse.getParsedResults().size() > 0 ? resultResponse.getParsedResults().get(0).getParsedText() : null;
    }

    @Override
    public String identifyWordWithMostOccurence(String textOcr) {
        String[] splitedString = textOcr.split(" ");
        int mostOccurenceWord = 0;
        String wordWithMostOccurence = null;
        for(String word : splitedString){
            if(textOcr.indexOf(word) > mostOccurenceWord){
                wordWithMostOccurence = word;
            }
        }

        return wordWithMostOccurence;
    }

    @Override
    public Boolean verifyCompanyByNameWithApiCall(String companyName) {
        ResponseAPISirene resultResponse = restTemplate.getForObject(ocrProperties.getUrl().get("api-sirene") + companyName, ResponseAPISirene.class);
        return true;
    }

    private String createFolderByCompanyName(String presumeCompanyName) {

        Path path = Paths.get(ocrProperties.getArchiveFolder() + "\\" + presumeCompanyName);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.getFileName().toString();
    }

}
