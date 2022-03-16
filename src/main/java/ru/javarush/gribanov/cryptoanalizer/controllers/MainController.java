package ru.javarush.gribanov.cryptoanalizer.controllers;

import ru.javarush.gribanov.cryptoanalizer.commands.Action;
import ru.javarush.gribanov.cryptoanalizer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters){
        Action action = Actions.find(actionName);
        Result result = action.execute(parameters);
        return result;
    }
}
