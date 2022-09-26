package com.ocrecognize.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultDto {

    @JsonProperty(value="results")
    private List<CompanyDto> results;

    @JsonProperty(value="total_results")
    private Integer total_results;

    @JsonProperty(value="page")
    private Integer page;

    @JsonProperty(value="per_page")
    private Integer per_page;

    @JsonProperty(value="total_pages")
    private Integer total_pages;
}
