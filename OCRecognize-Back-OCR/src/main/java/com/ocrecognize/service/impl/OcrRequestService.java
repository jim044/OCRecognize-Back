package com.ocrecognize.service.impl;

import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.model.pojo.ResponseAPISirene;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IOcrRequestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrRequestService implements IOcrRequestService {

    @Autowired
    private OCRProperties ocrProperties;

    @Autowired
    @Qualifier("ClientAuthentificationRestemplate")
    RestTemplate restTemplate;

    @Override
    public Boolean archiveDocumentByUrl(String url, String ocrAPICompany) throws IOException {
        String getAllTextByUrlAndByOCRApiCompany = getAllTextByUrlAndByOCRApiCompany(url, ocrAPICompany);

        if(getAllTextByUrlAndByOCRApiCompany != null){
            String presumeCompanyName = identifyWordWithMostOccurence(getAllTextByUrlAndByOCRApiCompany);
            if(verifyCompanyByNameWithApiCall(presumeCompanyName) != null){
                String folderUri = createFolderByCompanyName(presumeCompanyName);
                dowloadImageByUrl(url, folderUri);
            }
        }

        return true;
    }

    @Override
    public String getAllTextByUrlAndByOCRApiCompany(String url, String ocrAPICompany) {
        ResponseOCRSpaceByUrl resultResponse = restTemplate.getForObject(ocrProperties.getUrl().get(ocrAPICompany).replace("{apiKey}", ocrProperties.getApiKey().get(ocrAPICompany)) + "&url=" + url + "&language=fre", ResponseOCRSpaceByUrl.class);
        return resultResponse.getParsedResults().size() > 0 ? resultResponse.getParsedResults().get(0).getParsedText() : null;
    }

    @Override
    public String identifyWordWithMostOccurence(String textOcr) {
        textOcr = textOcr.replaceAll("\\R", " ").replaceAll("[^a-zA-Z0-9]", " ");
        String[] splitedString = textOcr.split(" ");
        int mostOccurenceWord = 0;
        String wordWithMostOccurence = null;


        for(String word : splitedString){
            if(StringUtils.countMatches(textOcr, word) > mostOccurenceWord && !isNumeric(word)){
                wordWithMostOccurence = word;
            }
        }

        return wordWithMostOccurence;
    }

    private boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    @Override
    public ResponseAPISirene verifyCompanyByNameWithApiCall(String companyName) {
        return restTemplate.getForObject(ocrProperties.getUrl().get("api-sirene") + companyName + "?page=1&per_page=100", ResponseAPISirene.class);
    }

    private String createFolderByCompanyName(String presumeCompanyName) {

        Path path = Paths.get(ocrProperties.getArchiveFolder() + "\\" + presumeCompanyName);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path.toString();
    }

    private void dowloadImageByUrl(String urlString, String path) throws IOException {
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

        FileOutputStream fos = new FileOutputStream(path + "\\imageUrl.jpg");
        fos.write(response);
        fos.close();
    }

}
