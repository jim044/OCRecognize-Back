package com.ocrecognize.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SiegeDto {

    @JsonProperty(value="siret")
    private String siret;

    @JsonProperty(value="date_creation")
    private String date_creation;

    @JsonProperty(value="tranche_effectif_salarie")
    private String tranche_effectif_salarie;

    @JsonProperty(value="date_debut_activite")
    private String date_debut_activite;

    @JsonProperty(value="etat_administratif")
    private String etat_administratif;

    @JsonProperty(value="activite_principale")
    private String activite_principale;

    @JsonProperty(value="complement_adresse")
    private String complement_adresse;

    @JsonProperty(value="numero_voie")
    private String numero_voie;

    @JsonProperty(value="indice_repetition")
    private String indice_repetition;

    @JsonProperty(value="type_voie")
    private String type_voie;

    @JsonProperty(value="libelle_voie")
    private String libelle_voie;

    @JsonProperty(value="distribution_speciale")
    private String distribution_speciale;

    @JsonProperty(value="cedex")
    private String cedex;

    @JsonProperty(value="libelle_cedex")
    private String libelle_cedex;

    @JsonProperty(value="commune")
    private String commune;

    @JsonProperty(value="libelle_commune")
    private String libelle_commune;

    @JsonProperty(value="code_pays_etranger")
    private String code_pays_etranger;

    @JsonProperty(value="libelle_commune_etranger")
    private String libelle_commune_etranger;

    @JsonProperty(value="libelle_pays_etranger")
    private String libelle_pays_etranger;

    @JsonProperty(value="adresse_complete")
    private String adresse_complete;

    @JsonProperty(value="adresse_complete_secondaire")
    private String adresse_complete_secondaire;

    @JsonProperty(value="code_postal")
    private String code_postal;

    @JsonProperty(value="departement")
    private String departement;

    @JsonProperty(value="geo_id")
    private String geo_id;

    @JsonProperty(value="longitude")
    private String longitude;

    @JsonProperty(value="latitude")
    private String latitude;

    @JsonProperty(value="activite_principale_registre_metier")
    private String activite_principale_registre_metier;

}
