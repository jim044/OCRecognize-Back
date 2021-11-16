package com.ocrecognize.service.impl;

import com.ocrecognize.model.pojo.ParsedResults;
import com.ocrecognize.model.pojo.ResponseOCRSpaceByUrl;
import com.ocrecognize.service.IOcrRequestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OcrRequestService implements IOcrRequestService {

    @Override
    public String getAllText() {
        RestTemplate restTemplate = new RestTemplate();
        ParsedResults resultResponse = restTemplate.getForObject("https://api.ocr.space/parse/imageurl?apikey=f1128cb05e88957&url=http://i.imgur.com/fwxooMv.png", ParsedResults.class);
        return resultResponse.getTextOverlay().getParsedText();
    }

}
