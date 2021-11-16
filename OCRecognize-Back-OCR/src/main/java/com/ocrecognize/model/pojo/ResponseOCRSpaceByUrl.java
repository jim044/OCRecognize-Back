package com.ocrecognize.model.pojo;

import lombok.Data;

@Data
public class ResponseOCRSpaceByUrl {
    private ParsedResults parsedResults;

    private Integer oCRExitCode;

    private Boolean isErroredOnProcessing;

    private String processingTimeInMilliseconds;

    private String searchablePDFURL;
}
