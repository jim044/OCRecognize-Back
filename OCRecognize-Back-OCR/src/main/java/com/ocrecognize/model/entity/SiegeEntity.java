package com.ocrecognize.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SIEGE")
@Data
public class SiegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "siret")
    private String siret;

    @Column(name = "date_creation")
    private String date_creation;

    @Column(name = "tranche_effectif_salarie")
    private String tranche_effectif_salarie;

    @Column(name = "date_debut_activite")
    private String date_debut_activite;

    @Column(name = "etat_administratif")
    private String etat_administratif;

    @Column(name = "activite_principale")
    private String activite_principale;

    @Column(name = "complement_adresse")
    private String complement_adresse;

    @Column(name = "numero_voie")
    private String numero_voie;

    @Column(name = "indice_repetition")
    private String indice_repetition;

    @Column(name = "type_voie")
    private String type_voie;

    @Column(name = "libelle_voie")
    private String libelle_voie;

    @Column(name = "distribution_speciale")
    private String distribution_speciale;

    @Column(name = "cedex")
    private String cedex;

    @Column(name = "libelle_cedex")
    private String libelle_cedex;

    @Column(name = "commune")
    private String commune;

    @Column(name = "libelle_commune")
    private String libelle_commune;

    @Column(name = "code_pays_etranger")
    private String code_pays_etranger;

    @Column(name = "libelle_commune_etranger")
    private String libelle_commune_etranger;

    @Column(name = "libelle_pays_etranger")
    private String libelle_pays_etranger;

    @Column(name = "adresse_complete")
    private String adresse_complete;

    @Column(name = "adresse_complete_secondaire")
    private String adresse_complete_secondaire;

    @Column(name = "code_postal")
    private String code_postal;

    @Column(name = "departement")
    private String departement;

    @Column(name = "geo_id")
    private String geo_id;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "activite_principale_registre_metier")
    private String activite_principale_registre_metier;

    @OneToOne(mappedBy = "siege")
    private CompanyEntity company;

}
