package ru.javarush.gribanov.cryptoanalizer.controllers;

import ru.javarush.gribanov.cryptoanalizer.commands.Action;
import ru.javarush.gribanov.cryptoanalizer.commands.Decoder;
import ru.javarush.gribanov.cryptoanalizer.commands.Encoder;
import ru.javarush.gribanov.cryptoanalizer.exceptions.AppException;

public enum Actions {
    ENCODE(new Encoder()), DECODE(new Decoder());

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
