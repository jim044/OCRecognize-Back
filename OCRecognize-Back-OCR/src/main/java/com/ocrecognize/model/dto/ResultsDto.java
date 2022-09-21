package com.ocrecognize.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultsDto {

    @JsonProperty(value="siren")
    private String siren;

    @JsonProperty(value="siege")
    private SiegeDto siege;

    @JsonProperty(value="date_creation")
    private String date_creation;

    @JsonProperty(value="tranche_effectif_salarie")
    private String tranche_effectif_salarie;

    @JsonProperty(value="date_mise_a_jour")
    private String date_mise_a_jour;

    @JsonProperty(value="categorie_entreprise")
    private String categorie_entreprise;

    @JsonProperty(value="etat_administratif")
    private String etat_administratif;

    @JsonProperty(value="nom_raison_sociale")
    private String nom_raison_sociale;

    @JsonProperty(value="nature_juridique")
    private String nature_juridique;

    @JsonProperty(value="activite_principale")
    private String activite_principale;

    @JsonProperty(value="section_activite_principale")
    private String section_activite_principale;

    @JsonProperty(value="economie_sociale_solidaire")
    private String economie_sociale_solidaire;

    @JsonProperty(value="nom_complet")
    private String nom_complet;

    @JsonProperty(value="nombre_etablissements")
    private Integer nombre_etablissements;

    @JsonProperty(value="nombre_etablissements_ouverts")
    private Integer nombre_etablissements_ouverts;

    @JsonProperty(value="is_entrepreneur_individuel")
    private Boolean is_entrepreneur_individuel;

    @JsonProperty(value="dirigeants")
    private List<DirigeantsDto> dirigeants;
}
