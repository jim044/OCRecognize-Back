package com.ocrecognize.repository;

import com.ocrecognize.model.entity.FournisseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurEntity, Long> {

    @Query("SELECT f FROM FournisseurEntity f WHERE f.enseigne1Etablissement = ?1")
    List<FournisseurEntity> findAllByFournisseurName(String fournisseurName);
}
