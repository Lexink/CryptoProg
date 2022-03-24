package ru.javarush.gribanov.cryptoanalyzer.entity;

import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class Source {
    private char[] text;

    public Source(Path path) {
        getArray(path);
    }

    private void getArray(Path path){
        List<Character> list = new ArrayList<>();
        try (BufferedReader reader = newBufferedReader(path)) {

            while (reader.ready()) {
                list.add((char)reader.read());
            }
            this.text = new char[list.size()];
            for (int i = 0; i < list.size(); i++) {
                text[i] = list.get(i);
            }
        } catch (FileNotFoundException | NoSuchFileException e){
            throw new AppException("Файл по указанному пути не найден", e);
        } catch (IOException e){
            throw new AppException("Ошибка чтения файла", e);
        }
    }

    public char[] getArray() {
        return text;
    }
}
