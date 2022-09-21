package com.ocrecognize.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TextOverlay {

    @JsonProperty(value="Lines")
    private List<String> lines;

    @JsonProperty(value="HasOverlay")
    private Boolean hasOverlay;

    @JsonProperty(value="Message")
    private String message;

}
