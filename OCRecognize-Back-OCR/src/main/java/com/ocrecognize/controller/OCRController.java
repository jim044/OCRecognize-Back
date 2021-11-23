package com.ocrecognize.controller;

import com.ocrecognize.service.IOcrRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ocrrecognize")
public class OCRController {

    @Autowired
    private IOcrRequestService iOcrRequestService;

    @GetMapping("/archiveDocumentByUrl")
    public Boolean archiveDocumentByUrl() {
        return iOcrRequestService.archiveDocumentByUrl("https://static.s-sfr.fr/media/ass_premiere_facture_sfr_v2.png", "ocr-space");
    }
}
