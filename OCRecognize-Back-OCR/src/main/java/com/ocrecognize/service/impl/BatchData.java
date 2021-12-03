package com.ocrecognize.service.impl;

import com.ocrecognize.mapper.FournisseurMapper;
import com.ocrecognize.model.dto.FournisseurDto;
import com.ocrecognize.service.IBatchData;
import liquibase.util.csv.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;

@Service
@Slf4j
public class BatchData implements IBatchData {

    @Autowired
    private FournisseurMapper fournisseurMapper;


    @Override
    public void insertNewDataForFournisseur() {
        CSVReader reader = null;
        FournisseurDto fournisseurDto = new FournisseurDto();
        String fileName = "D:\\Projet OCRecognize\\BDD Etablissement Sirene\\StockEtablissementHistorique_utf8\\StockEtablissementHistorique_utf8.csv";
        try
        {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(fileName), ',', '\'', 1);

            String[] nextLine;

            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                fournisseurDto = fournisseurMapper.convertStringCollectionToFournisseurDto(nextLine);
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

        /**
         *Long startTime = System.nanoTime();
         *
         *         List<FournisseurPojo> fournisseurDtoList = null;
         *         try {
         *             fournisseurDtoList = new CsvToBeanBuilder(new FileReader(fileName))
         *                     .withType(FournisseurPojo.class)
         *                     .build()
         *                     .parse();
         *
         *             fournisseurDtoList.forEach(fournisseurPojo -> {
         *                 log.info(fournisseurPojo.getActivitePrincipaleEtablissement());
         *             });
         *         } catch (FileNotFoundException e) {
         *             e.printStackTrace();
         *         }
         *
         *         Long endTime = System.nanoTime();
         *         Long duration = (endTime - startTime);
         *         log.info("Duration : " + duration);
         */

    }

}
