package com.mowerr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    private static final String defaultPasswordFile = "passwords.txt";
    private static final String defaultDictionaryFile = "dictionary.txt";

    private static Consumer consumer;
    private static Thread consumerThread;
    private static BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
    private static ProducersManager producersManager = new ProducersManager();

    private static String passwordFile;

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        BeginningDialogWithUser();

        producersManager.CrackNewFile(passwordFile);
        SetupConsumer();

        DisplayAvailableCommands();
        System.out.println("Złamane hasła: ");
        UserInteractionLoop();

        ExitProcedure();
    }

    private static void ExitProcedure() throws InterruptedException {
        producersManager.StopThreads();
        consumer.Stop();
        consumerThread.join();
    }

    private static void DisplayAvailableCommands() {
        System.out.println();
        System.out.println("Dostępne komendy:");
        System.out.println("exit - wyjście z programu");
        System.out.println("stat - podsumowanie postępu łamania haseł");
        System.out.println("help - wyświetlenie dostępnych komend");
        System.out.println("\"nazwaPliku\" - podanie nowego pliku haseł");
        System.out.println();
    }

    private static void UserInteractionLoop() throws IOException, InterruptedException, NoSuchAlgorithmException {
        String command = "";
        while(!new String("exit").equals(command)){
            command = inputBuffer.readLine();
            
            if(command == null)
            	command ="help";
            
            switch (command){
                case "exit":
                    break;

                case "stat":
                    consumer.DisplayProgress();
                    break;

                case "help":
                    DisplayAvailableCommands();
                    break;

                default:
                    passwordFile = command;
                    consumer.ResetProgress();
                    producersManager.CrackNewFile(passwordFile);
                    break;
            }
        }
    }

    private static void BeginningDialogWithUser() throws IOException {
        String input;
        System.out.println("Czy użyć domyślnej konfiguracji? [y-tak/n-nie]");
        input = inputBuffer.readLine();
        if(new String("n").equals(input))
            LoadUserConfiguration();
        else
            LoadDefaultConfiguration();
    }

    private static void LoadUserConfiguration() throws IOException {
        System.out.println();
        System.out.println("Brak wartości oznacza załadowanie pliku domyślnego");

        System.out.print("Plik słownika: ");
        String dictionary = inputBuffer.readLine();
        if(dictionary.isEmpty())
            dictionary = defaultDictionaryFile;

        System.out.print("Plik z hasłami: ");
        String passwords = inputBuffer.readLine();
        if(passwords.isEmpty())
            passwords = defaultPasswordFile;

        LoadConfiguration(dictionary, passwords);
    }

    private static void LoadDefaultConfiguration() throws IOException {
        LoadConfiguration(defaultDictionaryFile, defaultPasswordFile);
    }

    private static void SetupConsumer() {
        consumer = new Consumer();
        consumerThread = new Thread(consumer);
        consumerThread.start();
    }

    private static void LoadConfiguration(String dictionaryFile, String passwordFile) throws IOException {
        GlobalData globalData = GlobalData.getInstance();
        ArrayList<String> dictionary = FileLoader.LoadDictionary(dictionaryFile);
        globalData.setDictionary(dictionary);
        Main.passwordFile = passwordFile;
    }
}
