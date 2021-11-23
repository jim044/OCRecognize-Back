package com.ocrecognize.model.pojo;

import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.entity.CompanyEntity;
import lombok.Data;

import java.util.List;

@Data
public class ResponseAPISirene {

    private int total_results;
    private int total_pages;
    private int per_page;
    private int page;
    private List<CompanyDto> etablissement;
    private String spellcheck;
    private List<String> suggestions;

}
