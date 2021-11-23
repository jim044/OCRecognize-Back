package com.ocrecognize.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "ocr")
@Data
public class OCRProperties {

    private Map<String, String> apiKey;
    private Map<String, String> url;
    private String archiveFolder;
}
