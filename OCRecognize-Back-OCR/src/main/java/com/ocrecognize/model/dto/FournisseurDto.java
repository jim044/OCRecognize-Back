package com.ocrecognize.model.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.Column;

@Data
public class FournisseurDto {

    private Long id;

    @CsvBindByPosition(position = 0)
    private Long siren;

    @CsvBindByPosition(position = 1)
    private Long nic;

    @CsvBindByPosition(position = 2)
    private Long siret;

    @CsvBindByPosition(position = 3)
    private String dateFin;

    @CsvBindByPosition(position = 4)
    private String dateDebut;

    @CsvBindByPosition(position = 5)
    private String etatAdministratifEtablissement;

    @CsvBindByPosition(position = 6)
    private Boolean changementEtatAdministratifEtablissement;

    @CsvBindByPosition(position = 7)
    private String enseigne1Etablissement;

    @CsvBindByPosition(position = 8)
    private String enseigne21Etablissement;

    @CsvBindByPosition(position = 9)
    private String enseigne3Etablissement;

    @CsvBindByPosition(position = 10)
    private Boolean changementEnseigneEtablissement;

    @CsvBindByPosition(position = 11)
    private String denominationUsuelleEtablissement;

    @CsvBindByPosition(position = 12)
    private Boolean changementDenominationUsuelleEtablissement;

    @CsvBindByPosition(position = 13)
    private String activitePrincipaleEtablissement;

    @CsvBindByPosition(position = 14)
    private String nomenclatureActivitePrincipaleEtablissement;

    @CsvBindByPosition(position = 15)
    private Boolean changementActivitePrincipaleEtablissement;

    @CsvBindByPosition(position = 16)
    private String caractereEmployeurEtablissement;

    @CsvBindByPosition(position = 17)
    private Boolean changementCaractereEmployeurEtablissement;
}
