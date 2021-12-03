package com.ocrecognize.model.pojo;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class FournisseurPojo {

    @CsvBindByPosition(position = 0)
    private String siren;

    @CsvBindByPosition(position = 1)
    private String nic;

    @CsvBindByPosition(position = 2)
    private String siret;

    @CsvBindByPosition(position = 3)
    private String dateFin;

    @CsvBindByPosition(position = 4)
    private String dateDebut;

    @CsvBindByPosition(position = 5)
    private String etatAdministratifEtablissement;

    @CsvBindByPosition(position = 6)
    private String changementEtatAdministratifEtablissement;

    @CsvBindByPosition(position = 7)
    private String enseigne1Etablissement;

    @CsvBindByPosition(position = 8)
    private String enseigne21Etablissement;

    @CsvBindByPosition(position = 9)
    private String enseigne3Etablissement;

    @CsvBindByPosition(position = 10)
    private String changementEnseigneEtablissement;

    @CsvBindByPosition(position = 11)
    private String denominationUsuelleEtablissement;

    @CsvBindByPosition(position = 12)
    private String changementDenominationUsuelleEtablissement;

    @CsvBindByPosition(position = 13)
    private String activitePrincipaleEtablissement;

    @CsvBindByPosition(position = 14)
    private String nomenclatureActivitePrincipaleEtablissement;

    @CsvBindByPosition(position = 15)
    private String changementActivitePrincipaleEtablissement;

    @CsvBindByPosition(position = 16)
    private String caractereEmployeurEtablissement;

    @CsvBindByPosition(position = 17)
    private String changementCaractereEmployeurEtablissement;

}
