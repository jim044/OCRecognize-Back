package com.ocrecognize.service.impl;

import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.service.IBatchData;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BatchData implements IBatchData {

    @Override
    public void insertNewDataForFournisseur() {
        String fileName = "D:\\Projet OCRecognize\\BDD Etablissement Sirene\\StockEtablissementHistorique_utf8\\StockEtablissementHistorique_utf8.csv";

        List<FournisseurDto> fournisseurDtoList = null;
        try {
            fournisseurDtoList = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(FournisseurDto.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
