package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.model.entity.FournisseurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FournisseurMapper {

    FournisseurDto fournisseurEntityToFournisseurDto(FournisseurEntity fournisseurEntity);

    FournisseurEntity fournisseurDtoToFournisseurEntity(FournisseurDto fournisseurDto);

    List<FournisseurDto> fournisseursEntitesToFournisseursDtos(List<FournisseurEntity> fournisseursEntities);

    List<FournisseurEntity> fournisseursDtosToFournisseursEntities(List<FournisseurDto> fournisseursDtos);

    default FournisseurDto convertStringCollectionToFournisseurDto(String[] collectionFournisseur){
        FournisseurDto fournisseurDto = new FournisseurDto();

        fournisseurDto.setSiren(Long.valueOf(collectionFournisseur[0]));
        fournisseurDto.setNic(Long.valueOf(collectionFournisseur[1]));
        fournisseurDto.setSiret(Long.valueOf(collectionFournisseur[2]));
        fournisseurDto.setDateFin(collectionFournisseur[3]);
        fournisseurDto.setDateDebut(collectionFournisseur[4]);
        fournisseurDto.setEtatAdministratifEtablissement(collectionFournisseur[5]);
        fournisseurDto.setChangementEtatAdministratifEtablissement(Boolean.valueOf(collectionFournisseur[6]));
        fournisseurDto.setEnseigne1Etablissement(collectionFournisseur[7]);
        fournisseurDto.setEnseigne21Etablissement(collectionFournisseur[8]);
        fournisseurDto.setEnseigne3Etablissement(collectionFournisseur[9]);
        fournisseurDto.setChangementEnseigneEtablissement(Boolean.valueOf(collectionFournisseur[10]));
        fournisseurDto.setDenominationUsuelleEtablissement(collectionFournisseur[11]);
        fournisseurDto.setChangementDenominationUsuelleEtablissement(Boolean.valueOf(collectionFournisseur[12]));
        fournisseurDto.setActivitePrincipaleEtablissement(collectionFournisseur[13]);
        fournisseurDto.setNomenclatureActivitePrincipaleEtablissement(collectionFournisseur.length > 14 ? collectionFournisseur[14] : null);
        fournisseurDto.setChangementActivitePrincipaleEtablissement(collectionFournisseur.length > 15 ? Boolean.valueOf(collectionFournisseur[15]) : null);
        fournisseurDto.setCaractereEmployeurEtablissement(collectionFournisseur.length > 16 ? collectionFournisseur[16] : null);
        fournisseurDto.setChangementCaractereEmployeurEtablissement(collectionFournisseur.length > 17 ? Boolean.valueOf(collectionFournisseur[17]) : null);

        return fournisseurDto;
    }
}
