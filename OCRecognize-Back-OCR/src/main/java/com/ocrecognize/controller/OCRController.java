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

    @GetMapping("/getAllTextByUrl")
    public String getAllTextByUrl() {
        return iOcrRequestService.getAllText();
    }
}
