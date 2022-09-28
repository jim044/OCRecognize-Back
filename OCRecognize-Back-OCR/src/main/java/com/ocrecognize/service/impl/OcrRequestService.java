package com.ocrecognize.service.impl;

import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.dao.CompanyDao;
import com.ocrecognize.dao.FournisseurDao;
import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IBatchData;
import com.ocrecognize.service.IOcrRequestService;
import com.ocrecognize.utils.DateUtils;
import com.ocrecognize.utils.DownloadUtils;
import com.ocrecognize.utils.UtilsString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private CompanyDao companyDao;

    @Override
    public Boolean archiveDocumentByFile(String file) {
        String getAllTextByFileAndByOCRApiCompanyByGetRequest = getAllTextByFileAndByOCRApiCompanyByGetRequest(file, "ocr-space");
        return null;
    }

    @Override
    public Boolean archiveDocumentByUrl(String url, String ocrAPICompany) {
        String getAllTextByUrlAndByOCRApiCompanyByGetRequest = getAllTextByUrlAndByOCRApiCompanyByGetRequest(url, "ocr-space");
        
        if(getAllTextByUrlAndByOCRApiCompanyByGetRequest != null){
            String presumeFournisseurName = getFournisseurNameByOCRString(getAllTextByUrlAndByOCRApiCompanyByGetRequest);
            presumeFournisseurName = presumeFournisseurName.substring(0, 1).toUpperCase() + presumeFournisseurName.substring(1);
            if(presumeFournisseurName != null){
                String folderUri = createFolderByFournisseurName(presumeFournisseurName);
                DownloadUtils.downloadFileByURL(url, folderUri + "\\" + presumeFournisseurName + " " + DateUtils.formatLocalDateTime(LocalDateTime.now()) + ".jpg");
            }
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
        if(resultResponse != null && resultResponse.getParsedResults() != null && !resultResponse.getParsedResults().isEmpty()){
            returnResultResponse = resultResponse.getParsedResults().get(0).getParsedText();
        }

        return returnResultResponse;
    }

    public String getFournisseurNameByOCRString(String textOcr){
        textOcr = UtilsString.formatStringWithoutSpecialChar(textOcr);
        String[] splitedString = UtilsString.splitStringByTab(textOcr);
        String presumeFournisseurName = null;
        final String[] companyReturn = {null};
        final Integer[] firstIndex = {0};

        List<String> splitedStringList = Arrays.stream(splitedString)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

/*        String companyContenateString = splitedStringList
                .stream()
                .map(company -> "'" + company + "%'")
                .collect(Collectors.joining(" OR nom_complet LIKE "));*/

/*        List<CompanyDto> listCompanyDtoCheckByString = new ArrayList<>();
        splitedStringList.forEach(companyString -> {
            Stream.concat(listCompanyDtoCheckByString.stream(), companyDao.findCompanyByText(companyString).stream());
        });*/

        List<CompanyDto> companyDtoList = companyDao.findCompanyBySplitText(splitedStringList);
        Collections.reverse(companyDtoList);
        if(companyDtoList.size() > 0){
            companyDtoList.stream()
                    .map(companyDto -> companyDto.getNom_complet())
                    .collect(Collectors.toList())
                            .forEach(companyEtab -> {
                                Integer indexList = splitedStringList.indexOf(companyEtab);
                                if(companyReturn[0] == null){
                                    firstIndex[0] = indexList;
                                    companyReturn[0] = companyEtab;
                                }else if(indexList > 0 && firstIndex[0] == 0 && companyReturn[0] != null){
                                    firstIndex[0] = indexList;
                                    companyReturn[0] = companyEtab;
                                }else if(indexList > 0 && firstIndex[0] > 0 && indexList < firstIndex[0] && companyReturn[0] != null){
                                    firstIndex[0] = indexList;
                                    companyReturn[0] = companyEtab;
                                }

                            });
            presumeFournisseurName = companyReturn[0];
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
