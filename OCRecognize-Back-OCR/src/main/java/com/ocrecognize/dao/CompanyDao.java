package com.ocrecognize.dao;

import com.ocrecognize.mapper.CompanyMapper;
import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.dto.ResultDto;
import com.ocrecognize.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompanyDao {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    public List<CompanyDto> findCompanyBySplitText(List<String> splitedString) {
        //return companyMapper.companysEntitesToCompanysDtos(entityManager.createQuery("SELECT c FROM CompanyEntity c WHERE nom_complet LIKE " + splitedString + " AND LENGTH(c.nom_complet) > 2").getResultList());
        return companyMapper.companysEntitesToCompanysDtos(companyRepository.findCompanyBySplitText(splitedString));
    }

    public List<CompanyDto> findCompanyByText(String companyString) {
        return companyMapper.companysEntitesToCompanysDtos(companyRepository.findByNomCompletContaining(companyString));
    }
}
