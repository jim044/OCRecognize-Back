package com.ocrecognize.dao;

import com.ocrecognize.mapper.FournisseurMapper;
import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FournisseurDao {

    @Autowired
    private FournisseurMapper fournisseurMapper;

    @Autowired
    FournisseurRepository fournisseurRepository;

    public List<FournisseurDto> getFournisseurByFournisseurName(String fournisseurName){
        return fournisseurMapper.fournisseursEntitesToFournisseursDtos(fournisseurRepository.findAllByFournisseurName(fournisseurName));
    }

    public FournisseurDto saveAndUpdateFournisseur(FournisseurDto fournisseurDto) {
        return fournisseurMapper.fournisseurEntityToFournisseurDto(fournisseurRepository.save(fournisseurMapper.fournisseurDtoToFournisseurEntity(fournisseurDto)));
    }

    public void deleteAll(){
        fournisseurRepository.deleteAll();
    }
}
