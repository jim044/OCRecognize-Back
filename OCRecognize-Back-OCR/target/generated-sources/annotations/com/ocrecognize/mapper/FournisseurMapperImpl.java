package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.model.entity.FournisseurEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-29T10:18:39+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.2 (Oracle Corporation)"
)
@Component
public class FournisseurMapperImpl implements FournisseurMapper {

    @Override
    public FournisseurDto fournisseurEntityToFournisseurDto(FournisseurEntity fournisseurEntity) {
        if ( fournisseurEntity == null ) {
            return null;
        }

        FournisseurDto fournisseurDto = new FournisseurDto();

        fournisseurDto.setId( fournisseurEntity.getId() );
        fournisseurDto.setSiren( fournisseurEntity.getSiren() );
        fournisseurDto.setNic( fournisseurEntity.getNic() );
        fournisseurDto.setSiret( fournisseurEntity.getSiret() );
        fournisseurDto.setDateFin( fournisseurEntity.getDateFin() );
        fournisseurDto.setDateDebut( fournisseurEntity.getDateDebut() );
        fournisseurDto.setEtatAdministratifEtablissement( fournisseurEntity.getEtatAdministratifEtablissement() );
        fournisseurDto.setChangementEtatAdministratifEtablissement( fournisseurEntity.getChangementEtatAdministratifEtablissement() );
        fournisseurDto.setEnseigne1Etablissement( fournisseurEntity.getEnseigne1Etablissement() );
        fournisseurDto.setEnseigne21Etablissement( fournisseurEntity.getEnseigne21Etablissement() );
        fournisseurDto.setEnseigne3Etablissement( fournisseurEntity.getEnseigne3Etablissement() );
        fournisseurDto.setChangementEnseigneEtablissement( fournisseurEntity.getChangementEnseigneEtablissement() );
        fournisseurDto.setDenominationUsuelleEtablissement( fournisseurEntity.getDenominationUsuelleEtablissement() );
        fournisseurDto.setChangementDenominationUsuelleEtablissement( fournisseurEntity.getChangementDenominationUsuelleEtablissement() );
        fournisseurDto.setActivitePrincipaleEtablissement( fournisseurEntity.getActivitePrincipaleEtablissement() );
        fournisseurDto.setNomenclatureActivitePrincipaleEtablissement( fournisseurEntity.getNomenclatureActivitePrincipaleEtablissement() );
        fournisseurDto.setChangementActivitePrincipaleEtablissement( fournisseurEntity.getChangementActivitePrincipaleEtablissement() );
        fournisseurDto.setCaractereEmployeurEtablissement( fournisseurEntity.getCaractereEmployeurEtablissement() );
        fournisseurDto.setChangementCaractereEmployeurEtablissement( fournisseurEntity.getChangementCaractereEmployeurEtablissement() );

        return fournisseurDto;
    }

    @Override
    public FournisseurEntity fournisseurDtoToFournisseurEntity(FournisseurDto fournisseurDto) {
        if ( fournisseurDto == null ) {
            return null;
        }

        FournisseurEntity fournisseurEntity = new FournisseurEntity();

        fournisseurEntity.setId( fournisseurDto.getId() );
        fournisseurEntity.setSiren( fournisseurDto.getSiren() );
        fournisseurEntity.setNic( fournisseurDto.getNic() );
        fournisseurEntity.setSiret( fournisseurDto.getSiret() );
        fournisseurEntity.setDateFin( fournisseurDto.getDateFin() );
        fournisseurEntity.setDateDebut( fournisseurDto.getDateDebut() );
        fournisseurEntity.setEtatAdministratifEtablissement( fournisseurDto.getEtatAdministratifEtablissement() );
        fournisseurEntity.setChangementEtatAdministratifEtablissement( fournisseurDto.getChangementEtatAdministratifEtablissement() );
        fournisseurEntity.setEnseigne1Etablissement( fournisseurDto.getEnseigne1Etablissement() );
        fournisseurEntity.setEnseigne21Etablissement( fournisseurDto.getEnseigne21Etablissement() );
        fournisseurEntity.setEnseigne3Etablissement( fournisseurDto.getEnseigne3Etablissement() );
        fournisseurEntity.setChangementEnseigneEtablissement( fournisseurDto.getChangementEnseigneEtablissement() );
        fournisseurEntity.setDenominationUsuelleEtablissement( fournisseurDto.getDenominationUsuelleEtablissement() );
        fournisseurEntity.setChangementDenominationUsuelleEtablissement( fournisseurDto.getChangementDenominationUsuelleEtablissement() );
        fournisseurEntity.setActivitePrincipaleEtablissement( fournisseurDto.getActivitePrincipaleEtablissement() );
        fournisseurEntity.setNomenclatureActivitePrincipaleEtablissement( fournisseurDto.getNomenclatureActivitePrincipaleEtablissement() );
        fournisseurEntity.setChangementActivitePrincipaleEtablissement( fournisseurDto.getChangementActivitePrincipaleEtablissement() );
        fournisseurEntity.setCaractereEmployeurEtablissement( fournisseurDto.getCaractereEmployeurEtablissement() );
        fournisseurEntity.setChangementCaractereEmployeurEtablissement( fournisseurDto.getChangementCaractereEmployeurEtablissement() );

        return fournisseurEntity;
    }

    @Override
    public List<FournisseurDto> fournisseursEntitesToFournisseursDtos(List<FournisseurEntity> fournisseursEntities) {
        if ( fournisseursEntities == null ) {
            return null;
        }

        List<FournisseurDto> list = new ArrayList<FournisseurDto>( fournisseursEntities.size() );
        for ( FournisseurEntity fournisseurEntity : fournisseursEntities ) {
            list.add( fournisseurEntityToFournisseurDto( fournisseurEntity ) );
        }

        return list;
    }

    @Override
    public List<FournisseurEntity> fournisseursDtosToFournisseursEntities(List<FournisseurDto> fournisseursDtos) {
        if ( fournisseursDtos == null ) {
            return null;
        }

        List<FournisseurEntity> list = new ArrayList<FournisseurEntity>( fournisseursDtos.size() );
        for ( FournisseurDto fournisseurDto : fournisseursDtos ) {
            list.add( fournisseurDtoToFournisseurEntity( fournisseurDto ) );
        }

        return list;
    }
}
