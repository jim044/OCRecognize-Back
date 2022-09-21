package com.ocrecognize.service.impl;

import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.dao.FournisseurDao;
import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IBatchData;
import com.ocrecognize.service.IOcrRequestService;
import com.ocrecognize.utils.DateUtils;
import com.ocrecognize.utils.DownloadUtils;
import com.ocrecognize.utils.UtilsString;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class OcrRequestService implements IOcrRequestService {

    @Autowired
    private OCRProperties ocrProperties;

    @Autowired
    @Qualifier("ClientAuthentificationRestemplate")
    private RestTemplate restTemplate;

    @Autowired
    private FournisseurDao fournisseurDao;

    @Autowired
    private IBatchData batchData;

    @Override
    public Boolean archiveDocumentByFile(String file) {
        String getAllTextByFileAndByOCRApiCompanyByGetRequest = getAllTextByFileAndByOCRApiCompanyByGetRequest(file, "ocr-space");
        return null;
    }

    @Override
    public Boolean archiveDocumentByUrl(String url, String ocrAPICompany) {
        batchData.insertNewDataForFournisseur();
        String getAllTextByUrlAndByOCRApiCompanyByGetRequest = getAllTextByUrlAndByOCRApiCompanyByGetRequest(url, "ocr-space");
        
        if(getAllTextByUrlAndByOCRApiCompanyByGetRequest != null){
            String presumeFournisseurName = getFournisseurNameByOCRString(getAllTextByUrlAndByOCRApiCompanyByGetRequest);
            //if(presumeFournisseurName != null){
                presumeFournisseurName = UtilsString.formatStringWithoutSpecialChar(presumeFournisseurName).replaceAll(" ", "");
                String folderUri = createFolderByFournisseurName(presumeFournisseurName);
                DownloadUtils.downloadFileByURL(url, folderUri + "\\" + presumeFournisseurName + " " + DateUtils.formatLocalDateTime(LocalDateTime.now()) + ".jpg");
           // }
        }

        return true;
    }

    @Override
    public String getAllTextByUrlAndByOCRApiCompanyByGetRequest(String url, String ocrAPICompany) {
        ResponseOCRSpaceByUrl resultResponse = restTemplate.getForObject(ocrProperties.getUrl().get(ocrAPICompany).replace("{apiKey}", ocrProperties.getApiKey().get(ocrAPICompany)) + "&url=" + url + "&language=fre", ResponseOCRSpaceByUrl.class);
        return getResultResponseByOCRApiCompany(resultResponse);
    }

    @Override
    public String getAllTextByFileAndByOCRApiCompanyByGetRequest(String file, String s) {
        return null;
    }

    private String getResultResponseByOCRApiCompany(ResponseOCRSpaceByUrl resultResponse){
        String returnResultResponse = null;
        if(resultResponse != null && !resultResponse.getParsedResults().isEmpty()){
            returnResultResponse = resultResponse.getParsedResults().get(0).getParsedText();
        }

        return returnResultResponse;
    }

    public String getFournisseurNameByOCRString(String textOcr){
        textOcr = UtilsString.formatStringWithoutSpecialChar(textOcr);
        String[] splitedString = UtilsString.splitStringByTab(textOcr);
        String presumeFournisseurName = null;
        Map<String, Integer> mapMostOccurence = new HashMap<>();
        Map<String, Integer> mapMostOccurenceByUpperCase = new HashMap<>();
        Map<String, Boolean> mapPresentInBoth = new HashMap<>();

        List<FournisseurDto> fournisseurDtoList = fournisseurDao.getFournisseurByFournisseurName(splitedString[0].toUpperCase());
        if(!fournisseurDtoList.isEmpty()){
            presumeFournisseurName = splitedString[0].toUpperCase();
        }else{
            mapMostOccurence = UtilsString.mostOccurenceInCollection(splitedString, textOcr);
            mapMostOccurenceByUpperCase = UtilsString.mostOccurenceUpperCaseInCollection(splitedString, textOcr);
            mapPresentInBoth = UtilsString.areEqualKeyValues(mapMostOccurence, mapMostOccurenceByUpperCase);
        }

        if(fournisseurDtoList.isEmpty() && presumeFournisseurName != null){
            fournisseurDtoList = fournisseurDao.getFournisseurByFournisseurName(presumeFournisseurName.toUpperCase());
            presumeFournisseurName = !fournisseurDtoList.isEmpty() ? presumeFournisseurName.toUpperCase() : null;
        }

        return presumeFournisseurName;
    }

    private String createFolderByFournisseurName(String presumeFournisseurName) {

        Path path = Paths.get(ocrProperties.getArchiveFolder(), presumeFournisseurName);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.toString();
    }

}
