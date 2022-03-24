package ru.javarush.gribanov.cryptoanalyzer.controllers;

import ru.javarush.gribanov.cryptoanalyzer.commands.Action;
import ru.javarush.gribanov.cryptoanalyzer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters){
        Action action = Actions.find(actionName);
        return action.execute(parameters);
    }
}
