package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.model.entity.FournisseurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FournisseurMapper {

    FournisseurDto fournisseurEntityToFournisseurDto(FournisseurEntity fournisseurEntity);

    FournisseurEntity fournisseurDtoToFournisseurEntity(FournisseurDto fournisseurDto);

    List<FournisseurDto> fournisseursEntitesToFournisseursDtos(List<FournisseurEntity> fournisseursEntities);

    List<FournisseurEntity> fournisseursDtosToFournisseursEntities(List<FournisseurDto> fournisseursDtos);
}
