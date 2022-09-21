package com.ocrecognize.service;

import java.io.FileNotFoundException;

public interface IBatchData {

    void downloadStockEtablissement() throws FileNotFoundException;
    void insertNewDataForFournisseur();
}
