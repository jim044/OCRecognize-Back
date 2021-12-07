package com.ocrecognize.service.impl;

import com.netflix.discovery.converters.Auto;
import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.dao.FournisseurDao;
import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IBatchData;
import com.ocrecognize.service.IOcrRequestService;
import com.ocrecognize.utils.DateUtils;
import com.ocrecognize.utils.UtilsString;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
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
    public Boolean archiveDocumentByUrl(String url, String ocrAPICompany) throws IOException {
        String getAllTextByUrlAndByOCRApiCompanyByGetRequest = getAllTextByUrlAndByOCRApiCompanyByGetRequest(url, "ocr-space");
        
        if(getAllTextByUrlAndByOCRApiCompanyByGetRequest != null){
            String presumeFournisseurName = getFournisseurNameByOCRString(getAllTextByUrlAndByOCRApiCompanyByGetRequest);
            if(presumeFournisseurName != null){
                String folderUri = createFolderByFournisseurName(presumeFournisseurName);
                dowloadImageByUrl(presumeFournisseurName, url, folderUri);
            }
        }

        return true;
    }

    @Override
    public String getAllTextByUrlAndByOCRApiCompanyByGetRequest(String url, String ocrAPICompany) {
        ResponseOCRSpaceByUrl resultResponse = restTemplate.getForObject(ocrProperties.getUrl().get(ocrAPICompany).replace("{apiKey}", ocrProperties.getApiKey().get(ocrAPICompany)) + "&url=" + url + "&language=fre", ResponseOCRSpaceByUrl.class);
        return getResultResponseByOCRApiCompany(resultResponse);
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

        List<FournisseurDto> fournisseurDtoList = fournisseurDao.getFournisseurByFournisseurName(splitedString[0].toUpperCase());
        if(!fournisseurDtoList.isEmpty()){
            presumeFournisseurName = splitedString[0].toUpperCase();
        }else{
            int mostOccurenceWord = 0;

            for(String word : splitedString){
                if(StringUtils.countMatches(textOcr, word) > mostOccurenceWord && !isNumeric(word)){
                    presumeFournisseurName = word;
                }
            }
        }

        if(fournisseurDtoList.isEmpty() && presumeFournisseurName != null){
            fournisseurDtoList = fournisseurDao.getFournisseurByFournisseurName(presumeFournisseurName.toUpperCase());
            presumeFournisseurName = !fournisseurDtoList.isEmpty() ? presumeFournisseurName.toUpperCase() : null;
        }

        return presumeFournisseurName;
    }

    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private String createFolderByFournisseurName(String presumeFournisseurName) {

        Path path = Paths.get(ocrProperties.getArchiveFolder() + "\\" + presumeFournisseurName);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.toString();
    }

    private void dowloadImageByUrl(String fournisseurName, String urlString, String path) throws IOException {
        URL url = new URL(urlString);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(path + "\\" + fournisseurName + " " + DateUtils.formatLocalDateTime(LocalDateTime.now()) + ".jpg");
        fos.write(response);
        fos.close();
    }

}
