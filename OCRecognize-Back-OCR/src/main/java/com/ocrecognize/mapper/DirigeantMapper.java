package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.DirigeantDto;
import com.ocrecognize.model.entity.DirigeantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DirigeantMapper {

    @Mapping(target = "company.dirigeants", ignore = true)
    DirigeantDto dirigeantsEntityToDirigeantDto(DirigeantEntity dirigeantsEntity);

    @Mapping(target = "company.dirigeants", ignore = true)
    DirigeantEntity dirigeantsDtoToDirigeantEntity(DirigeantDto dirigeantsDto);

    List<DirigeantDto> dirigeantsEntitesToDirigeantsDtos(List<DirigeantEntity> dirigeantsEntities);

    List<DirigeantEntity> dirigeantsDtosToDirigeantsEntities(List<DirigeantDto> dirigeantsDtos);
}
