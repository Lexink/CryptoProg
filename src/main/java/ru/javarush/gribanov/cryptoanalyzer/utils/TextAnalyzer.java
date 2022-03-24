package ru.javarush.gribanov.cryptoanalyzer.utils;

import java.util.StringTokenizer;

public class TextAnalyzer {
    private char[] textToAnalyze;

    public TextAnalyzer(char[] textToAnalyze) {
        this.textToAnalyze = textToAnalyze;
    }

    public boolean analyze (){
        String text = String.valueOf(textToAnalyze);
        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        if (tokenizer.countTokens() > 2) {
            int idxPoint = text.indexOf('.');
            int idxComma = text.indexOf(',');
            boolean checkPointSpace = textToAnalyze[idxPoint + 1] == ' ' && Character.isUpperCase(textToAnalyze[idxPoint + 2]);
            boolean checkCommaSpace = textToAnalyze[idxComma + 1] == ' ';
            if (checkPointSpace) {
                return true;
            } else if (checkCommaSpace){
                return true;
            }
        }
        return false;
    }
}
