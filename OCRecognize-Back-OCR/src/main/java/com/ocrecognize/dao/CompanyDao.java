package com.ocrecognize.dao;

import com.ocrecognize.mapper.CompanyMapper;
import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.dto.ResultDto;
import com.ocrecognize.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDao {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyDto saveAndUpdateCompany(CompanyDto companyDto) {
        return companyMapper.companyEntityToCompanyDto(companyRepository.save(companyMapper.companyDtoToCompanyEntity(companyDto)));
    }

    public List<CompanyDto> saveAllAndUpdateCompany(List<CompanyDto> companyDtoList) {
        companyDtoList.forEach(companyDto -> {
            companyDto.getDirigeants().forEach(dirigeantDto -> {
                dirigeantDto.setCompany(companyDto);
            });
        });
        return companyMapper.companysEntitesToCompanysDtos(companyRepository.saveAll(companyMapper.companysDtosToCompanysEntities(companyDtoList)));
    }
}
