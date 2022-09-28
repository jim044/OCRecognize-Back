package com.ocrecognize.repository;

import com.ocrecognize.model.dto.CompanyDto;
import com.ocrecognize.model.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    @Query("SELECT c FROM CompanyEntity c WHERE LOWER(c.nom_raison_sociale) IN (?1) AND LENGTH(c.nom_raison_sociale) > 2")
    List<CompanyEntity> findCompanyBySplitText(List<String> splitedString);

    List<CompanyEntity> findByNomCompletContaining(String companyString);
}
