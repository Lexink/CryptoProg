package ru.javarush.gribanov.cryptoanalyzer.controllers;

import ru.javarush.gribanov.cryptoanalyzer.commands.*;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

public enum Actions {
    ENCODE(new Encoder()), DECODE(new Decoder()), BRUTE_FORCE(new BruteForcer()), ANALYZE(new StatAnalyzer());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName){
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e) {
           throw new AppException();
        }
    }
}
