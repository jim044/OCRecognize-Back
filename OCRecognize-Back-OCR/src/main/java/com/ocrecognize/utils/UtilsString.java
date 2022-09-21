package com.ocrecognize.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UtilsString {

    public static String formatStringWithoutSpecialChar(String textToFormat){
        return textToFormat.replaceAll("\\R", " ").replaceAll("[^a-zA-Z0-9]", " ");
    }

    public static String[] splitStringByTab(String textToSplit){
        return textToSplit.split(" ");
    }

    public static Map<String, Integer> mostOccurenceInCollection(String[] splitedString, String text){
        int mostOccurenceWord = 1;
        Map<String, Integer> mapMostOccurence = new HashMap<>();

        for(String word : splitedString){
            if(StringUtils.countMatches(text, word) > 1 && !isNumeric(word) && word.length() > 1){
                mapMostOccurence.put(word, StringUtils.countMatches(text, word));
                mostOccurenceWord = StringUtils.countMatches(text, word);
            }
        }

        return mapMostOccurence;
    }

    public static Map<String, Integer> mostOccurenceUpperCaseInCollection(String[] splitedString, String text) {
        int mostOccurenceWord = 0;
        Map<String, Integer> mapMostOccurenceByUpperCase = new HashMap<>();

        for(String word : splitedString){
            if(StringUtils.countMatches(text, word) > 1 && !isNumeric(word) && word.length() > 1 && isAllUpper(word)){
                mapMostOccurenceByUpperCase.put(word, StringUtils.countMatches(text, word));
                mostOccurenceWord = StringUtils.countMatches(text, word);
            }
        }

        return mapMostOccurenceByUpperCase;
    }

    private static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private static boolean isAllUpper(String s) {
        for(char c : s.toCharArray()) {
            if(Character.isLetter(c) && Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, Boolean> areEqualKeyValues(Map<String, Integer> first, Map<String, Integer> second) {
        return first.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().equals(second.get(e.getKey()))));
    }
}
