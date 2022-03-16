package ru.javarush.gribanov.cryptoanalizer.commands;

import ru.javarush.gribanov.cryptoanalizer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
