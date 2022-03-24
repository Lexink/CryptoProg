package ru.javarush.gribanov.cryptoanalyzer.constants;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {
    private static final String RUS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String PUNCTUATION_MARKS = " .,:;!()?\"-\r\n\t";
    private static final String DIGITS = "0123456789";
    public static final char[] ALPHABET = (RUS + RUS.toLowerCase() + PUNCTUATION_MARKS + DIGITS).toCharArray();

    private static final Path DEFAULT_ENCODE_FILE= Paths.get(System.getProperty("user.dir") + File.separator + "DefaultTexts" + File.separator + "defaultToEncode.txt");

    public static void main(String[] args) {
        System.out.println(DEFAULT_ENCODE_FILE);
    }

}
