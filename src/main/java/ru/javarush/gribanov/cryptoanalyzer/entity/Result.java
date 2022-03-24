package ru.javarush.gribanov.cryptoanalyzer.entity;

import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Result {
    private final String textString;

    public Result(char[] text, Path path) {
        textString = String.valueOf(text);
        createOutFile(text, path);
    }

    private void createOutFile (char[] text, Path path){
        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new AppException("Невозможно создать файл", e);
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            throw new AppException("Невозможно записать файл");
        }
    }
    public void printText(){
        System.out.println(textString);
    }
}
