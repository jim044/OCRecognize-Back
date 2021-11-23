package com.ocrecognize.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ParsedResults {

    @JsonProperty(value="TextOverlay")
    private TextOverlay textOverlay;

    @JsonProperty(value="TextOrientation")
    private String textOrientation;

    @JsonProperty(value="FileParseExitCode")
    private Integer fileParseExitCode;

    @JsonProperty(value="ParsedText")
    private String parsedText;

    @JsonProperty(value="ErrorMessage")
    private Integer errorMessage;

    @JsonProperty(value="ErrorDetails")
    private Integer errorDetails;

}
