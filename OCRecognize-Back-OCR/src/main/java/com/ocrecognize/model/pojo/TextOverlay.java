package com.ocrecognize.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class TextOverlay {

    private List<String> lines;

    private String parsedText;

    private String errorMessage;

    private String errorDetails;
}
