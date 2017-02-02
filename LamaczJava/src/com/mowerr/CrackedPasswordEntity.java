package com.mowerr;

public class CrackedPasswordEntity {
    private String crackedPassword;
    private PasswordEntity passwordEntity;

    public CrackedPasswordEntity(PasswordEntity passwordEntity, String originalPassword){
        this.crackedPassword = originalPassword;
        this.passwordEntity = passwordEntity;
    }

    public String GetOriginalPassword(){
        return crackedPassword;
    }

    public PasswordEntity GetEntity(){
        return passwordEntity;
    }
}
