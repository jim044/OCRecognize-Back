package com.ocrecognize.service.impl;

import com.ocrecognize.config.OCRProperties;
import com.ocrecognize.dao.FournisseurDao;
import com.ocrecognize.mapper.FournisseurMapper;
import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.service.IBatchData;
import liquibase.util.csv.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;

@Service
@Slf4j
public class BatchData implements IBatchData {

    @Autowired
    private FournisseurMapper fournisseurMapper;

    @Autowired
    private FournisseurDao fournisseurDao;

    @Autowired
    private OCRProperties ocrProperties;

    @Override
    @Scheduled(cron = "15 * * * * ?")
    public void insertNewDataForFournisseur() {
        CSVReader reader = null;
        FournisseurDto fournisseurDto = new FournisseurDto();
        String fileName = "D:\\Projet OCRecognize\\BDD Etablissement Sirene\\StockEtablissementHistorique_utf8\\StockEtablissementHistorique_utf8.csv";
        try
        {
            reader = new CSVReader(new FileReader(ResourceUtils.getFile(ocrProperties.getStockEtablissement())), ',', '\'', 1);
            String[] nextLine;
            fournisseurDao.deleteAll();
            while ((nextLine = reader.readNext()) != null)
            {
                fournisseurDto = fournisseurMapper.convertStringCollectionToFournisseurDto(nextLine);
                try {
                    if(fournisseurDto.getEnseigne1Etablissement().length() < 249){
                        fournisseurDto = fournisseurDao.saveAndUpdateFournisseur(fournisseurDto);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
