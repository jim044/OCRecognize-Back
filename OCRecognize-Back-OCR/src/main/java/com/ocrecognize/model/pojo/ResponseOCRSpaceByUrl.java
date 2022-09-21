package com.ocrecognize.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseOCRSpaceByUrl {

    @JsonProperty(value="ParsedResults")
    private List<ParsedResults> parsedResults;

    @JsonProperty(value="OCRExitCode")
    private Integer oCRExitCode;

    @JsonProperty(value="IsErroredOnProcessing")
    private Boolean isErroredOnProcessing;

    @JsonProperty(value="ProcessingTimeInMilliseconds")
    private String processingTimeInMilliseconds;

    @JsonProperty(value="SearchablePDFURL")
    private String searchablePDFURL;
}
