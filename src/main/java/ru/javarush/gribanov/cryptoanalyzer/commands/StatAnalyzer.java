package ru.javarush.gribanov.cryptoanalyzer.commands;

import ru.javarush.gribanov.cryptoanalyzer.entity.Result;
import ru.javarush.gribanov.cryptoanalyzer.entity.Source;
import ru.javarush.gribanov.cryptoanalyzer.entity.StatAnalyzeEntity;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.nio.file.Path;
import java.util.*;

public class StatAnalyzer implements Action {
    private final Character UNKNOWN_CHARACTER = Character.valueOf('\u267B');
    @Override
    public Result execute(String[] parameters) {
        System.out.println("Не успел доделать");
        return null;
        /*if (parameters.length == 3){
            Path inputFile = Path.of(parameters[0]);
            Path dictionaryFile = Path.of(parameters[1]);
            Path outputFile = Path.of(parameters[2]);

            Source textToDecode = new Source(inputFile);
            Source dictionaryText = new Source(dictionaryFile);

            char[] charsToDecode = textToDecode.getArray();
            char[] charsOfDictionary = dictionaryText.getArray();

            Map<Character, Character> decodeMap = analyzeFrequency(charsToDecode, charsOfDictionary);
            boolean isTextGood = false;
            Scanner scanner = new Scanner(System.in);
            char[] decodedChars = decode(charsToDecode, decodeMap);
            String text = String.valueOf(decodedChars);
            while (!isTextGood){
                System.out.println(text);
                System.out.println("Проверьте результат дешифровки. Текст корректный? (Да/Нет");
                String userAnswer = scanner.nextLine();
                if (userAnswer.equalsIgnoreCase("да")){
                    isTextGood = true;
                    scanner.close();
                } else {
                    System.out.println("Укажите буквы для замены в формате: \"п=л\"");
                    userAnswer = scanner.nextLine();
                    char[] answerArray = userAnswer.toCharArray();
                    if (answerArray.length > 2) {
                        text = replaceChar(text, answerArray[0], answerArray[answerArray.length - 1]);
                    } else {
                        throw new AppException("Ответ введен в неверном формате");
                    }
                }
            }

            Result decodedFile = new Result(decodedChars, outputFile);
            return decodedFile;
        }
        throw new AppException("Неверное число параметров");*/
    }

    private char[] decode(char[] encodedChars, Map<Character, Character> mapForDecode){
        char[] decodedChars = new char[encodedChars.length];
        for (int i = 0; i < encodedChars.length; i++) {
            decodedChars[i] = mapForDecode.get(encodedChars[i]);
        }
        return decodedChars;
    }

    private String replaceChar(String text, char charToReplace, char newChar){
        String newText = text;
        newText.replace(newChar, '\u273A');
        newText.replace(charToReplace, newChar);
        newText.replace('\u273A', charToReplace);
        return newText;
    }

    private Map<Character, Character> analyzeFrequency(char[] encoderChars, char[] dictChars){
        StatAnalyzeEntity[] textEntities = getEntityArray(encoderChars);
        StatAnalyzeEntity[] dictEntities = getEntityArray(dictChars);

        System.out.println(Arrays.toString(textEntities));
        System.out.println(Arrays.toString(dictEntities));
        Map<Character, Character> decodedMap = new HashMap<>();
        for (int i = 0; i < textEntities.length; i++) {
            for (int j = 0; j < dictEntities.length; j++) {
                if (textEntities[i].equals(dictEntities[j])){
                    decodedMap.put(textEntities[i].getCharOfText(), dictEntities[j].getCharOfText());
                }
            }
            if (decodedMap.get(textEntities[i].getCharOfText()) == null){
                decodedMap.put(textEntities[i].getCharOfText(), UNKNOWN_CHARACTER);
            }
        }

        //System.out.println(decodedMap);
        return decodedMap;
    }

    private StatAnalyzeEntity[] getEntityArray(char[] chars){
        Set<Character> charsSet = new HashSet();
        for (int i = 0; i < chars.length; i++) {
            charsSet.add(Character.toLowerCase(chars[i]));
        }

        StatAnalyzeEntity[] entities = new StatAnalyzeEntity[charsSet.size()];
        int idx = 0;
        for (Character character : charsSet) {
            Double frequency = getCharCount(character, chars)/chars.length;
            entities[idx] = new StatAnalyzeEntity(character, frequency*100);
            idx++;
        }

        return entities;
    }

    private Double getCharCount(Character ch, char[] chars){
        Double count = 0.0;
        for (int i = 0; i < chars.length; i++) {
            if (ch.equals(Character.valueOf(chars[i]))){
                count = count + 1;
            }
        }
        return count;
    }
}
