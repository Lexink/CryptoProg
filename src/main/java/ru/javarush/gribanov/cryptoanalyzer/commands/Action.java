package ru.javarush.gribanov.cryptoanalyzer.commands;

import ru.javarush.gribanov.cryptoanalyzer.entity.Result;

public interface Action {
    Result execute(String[] parameters);
}
