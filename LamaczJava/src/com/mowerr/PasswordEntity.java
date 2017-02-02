package com.mowerr;

public class PasswordEntity {
    private String password;
    private boolean isCracked = false;

    PasswordEntity(String password){
        this.password = password;
    }

    public void setCracked(){
        isCracked = true;
    }

    public boolean isCracked(){
        return isCracked;
    }

    public String Get(){
        return password;
    }
}
