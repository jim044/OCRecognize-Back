package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.dto.ResultsDto;
import com.ocrecognize.model.entity.CompanyEntity;
import com.ocrecognize.model.entity.DirigeantsEntity;
import com.ocrecognize.model.entity.SiegeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SiegeMapper.class, DirigeantMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    ResultsDto companyEntityToCompanyDto(CompanyEntity companyEntity);

    CompanyEntity companyDtoToCompanyEntity(ResultsDto companyDto);

    List<ResultsDto> companysEntitesToCompanysDtos(List<CompanyEntity> companysEntities);

    List<CompanyEntity> companysDtosToCompanysEntities(List<ResultsDto> companysDtos);
}
