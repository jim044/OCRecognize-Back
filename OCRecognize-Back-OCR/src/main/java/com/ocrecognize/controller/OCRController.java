package com.ocrecognize.controller;

import com.ocrecognize.service.IOcrRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/ocrrecognize")
public class OCRController {

    @Autowired
    private IOcrRequestService iOcrRequestService;

    @GetMapping(path = "/archiveDocumentByUrl/")
    public Boolean archiveDocumentByUrl(@RequestParam String url) throws IOException {
        return iOcrRequestService.archiveDocumentByUrl(url, "ocr-space");
    }

    @PostMapping(path = "/archiveDocumentByFile")
    public Boolean archiveDocumentByFile(@RequestBody String file) throws IOException {
        return iOcrRequestService.archiveDocumentByFile(file);
    }
}
