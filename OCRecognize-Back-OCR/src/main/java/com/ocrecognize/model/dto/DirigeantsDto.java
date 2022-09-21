package com.ocrecognize.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirigeantsDto {

    @JsonProperty(value="nom")
    private String nom;

    @JsonProperty(value="prenoms")
    private String prenoms;

    @JsonProperty(value="annee_de_naissance")
    private String annee_de_naissance;

    @JsonProperty(value="qualite")
    private String qualite;
}
