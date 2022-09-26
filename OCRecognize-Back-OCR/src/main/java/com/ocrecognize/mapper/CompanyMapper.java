package com.ocrecognize.mapper;

import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.entity.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {SiegeMapper.class, DirigeantMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyDto companyEntityToCompanyDto(CompanyEntity companyEntity);

    CompanyEntity companyDtoToCompanyEntity(CompanyDto companyDto);

    List<CompanyDto> companysEntitesToCompanysDtos(List<CompanyEntity> companysEntities);

    List<CompanyEntity> companysDtosToCompanysEntities(List<CompanyDto> companysDtos);
}
