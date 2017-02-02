package com.mowerr;

import java.util.ArrayList;

public class GlobalData {
    private ArrayList<String> dictionary;
    private PasswordsDB passwordsDB;
    private ArrayList<CrackedPasswordEntity> crackedPasswords = new ArrayList<CrackedPasswordEntity>();
    private boolean crackingFinished = false;

    public void setCrackingFinished(boolean crackingFinished){ this.crackingFinished = crackingFinished; }

    public boolean isCrackingFinished() { return crackingFinished; }

    public void setDictionary(ArrayList<String> dictionary){
        this.dictionary = dictionary;
    }

    public void setPasswordsDB(PasswordsDB passwordsDB){ this.passwordsDB = passwordsDB; }

    public ArrayList<String> getDictionary(){
        return dictionary;
    }

    public PasswordsDB getPasswordsDB(){
        return passwordsDB;
    }

    public void AddCrackedPassword(CrackedPasswordEntity crackedPassword){ crackedPasswords.add(crackedPassword); }

    public CrackedPasswordEntity GetNextCrackedPassword() { return crackedPasswords.remove(0); }

    public boolean IsAnyCrackedPasswordLeft() { return !crackedPasswords.isEmpty(); }

    private static GlobalData instance = null;

    private GlobalData(){}

    public static GlobalData getInstance(){
        if(instance == null)
            instance = new GlobalData();

        return instance;
    }
}
