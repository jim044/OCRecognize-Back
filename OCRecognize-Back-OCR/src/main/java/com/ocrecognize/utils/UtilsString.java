package com.ocrecognize.utils;

public class UtilsString {

    public static String formatStringWithoutSpecialChar(String textToFormat){
        return textToFormat.replaceAll("\\R", " ").replaceAll("[^a-zA-Z0-9]", " ");
    }

    public static String[] splitStringByTab(String textToSplit){
        return textToSplit.split(" ");
    }
}
