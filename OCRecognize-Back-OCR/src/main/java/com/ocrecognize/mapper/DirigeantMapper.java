package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.DirigeantsDto;
import com.ocrecognize.model.entity.DirigeantsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DirigeantMapper {

    DirigeantsDto dirigeantsEntityToDirigeantDto(DirigeantsEntity dirigeantsEntity);

    DirigeantsEntity dirigeantsDtoToDirigeantEntity(DirigeantsDto dirigeantsDto);

    List<DirigeantsDto> dirigeantsEntitesToDirigeantsDtos(List<DirigeantsEntity> dirigeantsEntities);

    List<DirigeantsEntity> dirigeantsDtosToDirigeantsEntities(List<DirigeantsDto> dirigeantsDtos);
}
