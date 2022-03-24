package ru.javarush.gribanov.cryptoanalyzer.commands;

import ru.javarush.gribanov.cryptoanalyzer.constants.Constants;
import ru.javarush.gribanov.cryptoanalyzer.entity.Result;
import ru.javarush.gribanov.cryptoanalyzer.entity.Source;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;
import ru.javarush.gribanov.cryptoanalyzer.utils.TextAnalyzer;

import java.nio.file.Path;

public class BruteForcer implements Action {
    @Override
    public Result execute(String[] parameters) {
        if (parameters.length == 2){
            Path inputFile = Path.of(parameters[0]);
            Path outputFile = Path.of(parameters[1]);
            int offset;
            Source textToDecode = new Source(inputFile);

            char[] charsToDecode = textToDecode.getArray();
            char[] outText = new char[charsToDecode.length];
            Result decodedFile;
            for (offset = 0; offset < Constants.ALPHABET.length; offset ++) {
                for (int i = 0; i < charsToDecode.length; i++) {
                    int indexAlphabet = searchAlphabetIndex(charsToDecode[i]);
                    if (indexAlphabet >= 0) {
                        int newIndex = indexAlphabet - offset;
                        if (newIndex >= 0 && newIndex < Constants.ALPHABET.length){
                            outText[i] = Constants.ALPHABET[newIndex];
                        } else if (newIndex < 0){
                            outText[i] = Constants.ALPHABET[Constants.ALPHABET.length + newIndex];
                        } else {
                            outText[i] = Constants.ALPHABET[newIndex - Constants.ALPHABET.length];
                        }
                    } else {
                        outText[i] = charsToDecode[i];
                    }
                }
                TextAnalyzer textAnalyzer = new TextAnalyzer(outText);
                if (textAnalyzer.analyze()){
                    decodedFile = new Result(outText, outputFile);
                    return decodedFile;
                }
            }
                throw new AppException("Не удалось расшифровать файл");


        } else {
            throw new AppException("Неверное число параметров");
        }
    }

    private int searchAlphabetIndex (char key){
        for (int i = 0; i < Constants.ALPHABET.length; i++) {
            if (Constants.ALPHABET[i] == key){
                return i;
            }
        }
        return -1;
    }
}
