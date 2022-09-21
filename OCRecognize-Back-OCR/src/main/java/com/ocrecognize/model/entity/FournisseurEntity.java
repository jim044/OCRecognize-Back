package com.ocrecognize.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FOURNISSEUR")
@Data
public class FournisseurEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "siren")
    private Long siren;

    @Column(name = "nic")
    private Long nic;

    @Column(name = "siret")
    private Long siret;

    @Column(name = "datefin")
    private String dateFin;

    @Column(name = "datedebut")
    private String dateDebut;

    @Column(name = "etatadministratifetablissement")
    private String etatAdministratifEtablissement;

    @Column(name = "changementetatadministratifetablissement")
    private Boolean changementEtatAdministratifEtablissement;

    @Column(name = "enseigne1etablissement")
    private String enseigne1Etablissement;

    @Column(name = "enseigne2etablissement")
    private String enseigne21Etablissement;

    @Column(name = "enseigne3etablissement")
    private String enseigne3Etablissement;

    @Column(name = "changementenseigneetablissement")
    private Boolean changementEnseigneEtablissement;

    @Column(name = "denominationusuelleetablissement")
    private String denominationUsuelleEtablissement;

    @Column(name = "changementdenominationusuelleetablissement")
    private Boolean changementDenominationUsuelleEtablissement;

    @Column(name = "activiteprincipaleetablissement")
    private String activitePrincipaleEtablissement;

    @Column(name = "nomenclatureactiviteprincipaleetablissement")
    private String nomenclatureActivitePrincipaleEtablissement;

    @Column(name = "changementactiviteprincipaleetablissement")
    private Boolean changementActivitePrincipaleEtablissement;

    @Column(name = "caractereemployeuretablissement")
    private String caractereEmployeurEtablissement;

    @Column(name = "changementcaractereemployeuretablissement")
    private Boolean changementCaractereEmployeurEtablissement;
}
