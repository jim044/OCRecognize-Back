package com.ocrecognize.model.dto;

import lombok.Data;

@Data
public class FournisseurDto {

    private Long id;
    private Long siren;
    private Long nic;
    private Long siret;
    private String dateFin;
    private String dateDebut;
    private String etatAdministratifEtablissement;
    private Boolean changementEtatAdministratifEtablissement;
    private String enseigne1Etablissement;
    private String enseigne21Etablissement;
    private String enseigne3Etablissement;
    private Boolean changementEnseigneEtablissement;
    private String denominationUsuelleEtablissement;
    private Boolean changementDenominationUsuelleEtablissement;
    private String activitePrincipaleEtablissement;
    private String nomenclatureActivitePrincipaleEtablissement;
    private Boolean changementActivitePrincipaleEtablissement;
    private String caractereEmployeurEtablissement;
    private Boolean changementCaractereEmployeurEtablissement;
}
