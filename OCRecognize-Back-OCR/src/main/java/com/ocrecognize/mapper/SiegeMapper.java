package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.SiegeDto;
import com.ocrecognize.model.entity.SiegeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SiegeMapper {

    SiegeDto siegeEntityToSiegeDto(SiegeEntity siegeEntity);

    SiegeEntity siegeDtoToSiegeEntity(SiegeDto siegeDto);

    List<SiegeDto> siegesEntitesToSiegesDtos(List<SiegeEntity> siegesEntities);

    List<SiegeEntity> siegesDtosToSiegesEntities(List<SiegeDto> siegesDtos);
}
