package com.ocrecognize.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMPANY")
@Data
public class CompanyEntity {

    @Id
    @Column(name = "siren")
    private String siren;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "siege_id", referencedColumnName = "id")
    private SiegeEntity siege;

    @Column(name = "date_creation")
    private String date_creation;

    @Column(name = "tranche_effectif_salarie")
    private String tranche_effectif_salarie;

    @Column(name = "date_mise_a_jour")
    private String date_mise_a_jour;

    @Column(name = "categorie_entreprise")
    private String categorie_entreprise;

    @Column(name = "etat_administratif")
    private String etat_administratif;

    @Column(name = "nom_raison_sociale")
    private String nom_raison_sociale;

    @Column(name = "nature_juridique")
    private String nature_juridique;

    @Column(name = "activite_principale")
    private String activite_principale;

    @Column(name = "section_activite_principale")
    private String section_activite_principale;

    @Column(name = "economie_sociale_solidaire")
    private String economie_sociale_solidaire;

    @Column(name = "nom_complet")
    private String nomComplet;

    @Column(name = "nombre_etablissements")
    private Integer nombre_etablissements;

    @Column(name = "nombre_etablissements_ouverts")
    private Integer nombre_etablissements_ouverts;

    @Column(name = "is_entrepreneur_individuel")
    private Boolean is_entrepreneur_individuel;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<DirigeantEntity> dirigeants;
}
