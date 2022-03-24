package ru.javarush.gribanov.cryptoanalyzer;

import ru.javarush.gribanov.cryptoanalyzer.entity.Result;
import ru.javarush.gribanov.cryptoanalyzer.exceptions.AppException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleRunner {
    private final static String INTRO = """
            Введите желаемое действие:
            encode - для шифрования шифром Цезаря
            decode - для дешифровки текста, зашифрованного шифром Цезаря
            brute_force - для  дешифровки текста метедом BruteForce
            analyze - для дешифровки текста методом статистического анализа
            exit - для выхода из программы""";
    private static final String HELP_SOURCE_FILE = "Введите путь к исходному файлу с текстом: ";
    private static final String HELP_ENCRYPTED_SOURCE_FILE = "Введите путь к файлу с зашифрованным текстом: ";
    private static final String HELP_DESTINATION_FILE = "Введите путь к выходному файлу: ";
    private static final String HELP_DICTIONARY_FILE = "Введите путь к файлу с текстом-словарем: ";
    private static final String HELP_KEY = "Введите ключ: ";

    public static void main(String[] args) {

        Result result;
        Application application = new Application();
        if (args.length == 0){
            System.out.println(INTRO);
            String action, pathIn, pathOut, key, pathDictionary;
            String[] parameters;
            Scanner scanner = new Scanner(System.in);
            action = scanner.nextLine();
            if (action.equalsIgnoreCase("encode") || action.equalsIgnoreCase("decode")){
                parameters = new String[4];
                pathIn = getInputFilePath(scanner, HELP_SOURCE_FILE);
                pathOut = getOutputFilePath(scanner);
                System.out.println(HELP_KEY);
                key = scanner.nextLine();
                parameters[0] = action;
                parameters[1] = pathIn;
                parameters[2] = pathOut;
                parameters[3] = key;
            } else if (action.equalsIgnoreCase("brute_force")){
                parameters = new String[3];
                pathIn = getInputFilePath(scanner, HELP_ENCRYPTED_SOURCE_FILE);
                pathOut = getOutputFilePath(scanner);

                parameters[0] = action;
                parameters[1] = pathIn;
                parameters[2] = pathOut;

            } else if (action.equalsIgnoreCase("analyze")){
                parameters = new String[4];
                pathIn = getInputFilePath(scanner, HELP_ENCRYPTED_SOURCE_FILE);
                pathDictionary = getInputFilePath(scanner, HELP_DICTIONARY_FILE);
                pathOut = getOutputFilePath(scanner);

                parameters[0] = action;
                parameters[1] = pathIn;
                parameters[2] = pathDictionary;
                parameters[3] = pathOut;
            } else if (action.equalsIgnoreCase("exit")){
                parameters = new String[0];
                System.exit(0);
            }
            else {
                throw new AppException("Введеное действие не существует");
            }
            scanner.close();
            result = application.run(parameters);
            result.printText();
        } else {
            result = application.run(args);
            result.printText();
        }
    }

    private static String getInputFilePath(Scanner scanner, String helpText){
        System.out.println(helpText);
        String pathString = scanner.nextLine();
        while (true){
            if (Files.exists(Path.of(pathString))){
                return pathString;
            } else {
                System.out.println("Файла по указанному пути не существует, введите корректный путь к файлу: ");
                pathString = scanner.nextLine();
            }
        }
    }

    private static String getOutputFilePath(Scanner scanner){
        System.out.println(HELP_DESTINATION_FILE);
        String pathString = scanner.nextLine();
        String answer;
        while (true){
            if (Files.exists(Path.of(pathString))){
                System.out.println("Такой файл уже существует, хотите перезаписать файл? Введите да/нет: ");
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("да")) {
                    return pathString;
                } else if (answer.equalsIgnoreCase("нет")){
                    System.out.println("Укажите новый путь к файлу: ");
                    pathString = scanner.nextLine();
                } else {
                    throw new AppException("Указан неверный ответ!");
                }
            } else {
                return pathString;
            }
        }
    }
}
