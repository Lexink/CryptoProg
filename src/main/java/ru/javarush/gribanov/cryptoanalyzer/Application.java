package ru.javarush.gribanov.cryptoanalyzer;

import ru.javarush.gribanov.cryptoanalyzer.controllers.MainController;
import ru.javarush.gribanov.cryptoanalyzer.entity.Result;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.util.Arrays;

public class Application {

    private final MainController mainController;

    public Application() {
        mainController = new MainController();
    }

    public Result run(String[] args){
        if (args.length > 0){
            String action = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return  mainController.doAction(action, parameters);
        }
        throw new AppException();
    }

}
