package com.ocrecognize.model.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAPISirene {

    private int total_results;
    private int total_pages;
    private int per_page;
    private int page;
    private List<CompanyFind> etablissement;
    private String spellcheck;
    private List<String> suggestions;

}
