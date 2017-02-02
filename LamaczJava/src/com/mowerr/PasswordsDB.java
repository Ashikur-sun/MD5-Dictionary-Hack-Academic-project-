package com.mowerr;

import java.util.ArrayList;
import java.util.Iterator;

public class PasswordsDB implements Iterable<PasswordEntity>{
    private ArrayList<PasswordEntity> passwords = new ArrayList<PasswordEntity>();

    public void Add(String password){
        PasswordEntity entity = new PasswordEntity(password);
        passwords.add(entity);
    }

    public PasswordEntity Get(int index){
        return passwords.get(index);
    }

    public int GetNumberOfPasswords() { return passwords.size(); }

    
    @Override
    public Iterator<PasswordEntity> iterator() {
        return passwords.iterator();
    }
}
