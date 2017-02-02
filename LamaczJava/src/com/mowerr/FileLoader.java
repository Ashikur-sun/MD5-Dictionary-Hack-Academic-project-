package com.mowerr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class FileLoader {
    private FileLoader(){}

    public static ArrayList<String> LoadDictionary(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        ArrayList<String> result = new ArrayList<String>();

        for(String line; (line = bufferedReader.readLine()) != null; ){
            if(!line.isEmpty() && !isNumeric(line))
                result.add(line);
        }
        
        bufferedReader.close();
        return result;
    }

    public static PasswordsDB LoadPasswords(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        PasswordsDB passwordsDB = new PasswordsDB();

        for(String line; (line = bufferedReader.readLine()) != null; ){
            if(!line.isEmpty())
                passwordsDB.Add(line);
        }
        
        bufferedReader.close();
        return passwordsDB;
    }

    private static boolean isNumeric(String s) {
        return s.matches("\\d*\\.?\\d+");
    }
}
