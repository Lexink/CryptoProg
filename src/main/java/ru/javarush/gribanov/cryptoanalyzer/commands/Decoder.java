package ru.javarush.gribanov.cryptoanalyzer.commands;

import ru.javarush.gribanov.cryptoanalyzer.constants.Constants;
import ru.javarush.gribanov.cryptoanalyzer.entity.Result;
import ru.javarush.gribanov.cryptoanalyzer.entity.Source;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.nio.file.Path;

public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        if (parameters.length == 3){
            Path inputFile = Path.of(parameters[0]);
            Path outputFile = Path.of(parameters[1]);
            int offset;
            try {
                offset = Integer.parseInt(parameters[2]);
                if (offset > Constants.ALPHABET.length){
                    offset = offset % Constants.ALPHABET.length;
                }

            } catch (NumberFormatException e) {
                throw new AppException("Параметр сдвига введен не корректно", e);
            }
            Source textToDecode = new Source(inputFile);

            char[] charsToDecode = textToDecode.getArray();
            char[] outText = new char[charsToDecode.length];
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
            return new Result(outText, outputFile);
        }
        throw new AppException("Неверное число параметров");
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
