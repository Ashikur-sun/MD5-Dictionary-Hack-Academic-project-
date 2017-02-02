package com.mowerr;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Producer implements Runnable{
    private IGuessGenerator guessGenerator;
    private PasswordsDB passwordsDB;

    private MessageDigest md = MessageDigest.getInstance("MD5");

    private boolean isRunning = true;


    private Producer(IGuessGenerator guessGenerator) throws NoSuchAlgorithmException {
        this.guessGenerator = guessGenerator;
        this.passwordsDB = GlobalData.getInstance().getPasswordsDB();
    }

    public void Stop(){
        isRunning = false;
    }

    @Override
    public void run() {
        while(isRunning && !GlobalData.getInstance().isCrackingFinished()) {
            String guess = guessGenerator.GetNextGuess();

            if(guess.isEmpty()){
            	break;
            }
            
            byte[] guessBytes = guess.getBytes(Charset.forName("UTF-8"));
            byte[] guessMD5 = md.digest(guessBytes);
            

            	for(int i = 0; i < passwordsDB.GetNumberOfPasswords(); i++){
            		PasswordEntity password = passwordsDB.Get(i);
            		if(!password.isCracked()){
            			byte[] passwordMD5 = DatatypeConverter.parseHexBinary(password.Get());
                       
            			if(MessageDigest.isEqual(passwordMD5, guessMD5) && !password.isCracked()) {
            				synchronized (GlobalData.getInstance()){
            					CrackedPasswordEntity crackedPassword = new CrackedPasswordEntity(password, guess);
            					GlobalData.getInstance().AddCrackedPassword(crackedPassword);
            					GlobalData.getInstance().notify();
            				}
            			}
            		}           	
            }
        }
    }


    public static class Builder{
        private IGuessAffixApplier affixApplier;
        private IGuessTransform transform;
        private IGuessGenerator guessGenerator;

        public Builder affixApplier(AffixApplier affixApplier){
            this.affixApplier = Factory.affixApplier(affixApplier);
            return this;
        }

        public Builder transform(Transform transform){
            this.transform = Factory.transform(transform);
            return this;
        }

        public Builder type(GuessType type){
            this.guessGenerator = Factory.guessGenerator(type, transform, affixApplier);
            return this;
        }

        public Producer Build() throws NoSuchAlgorithmException {
            return new Producer(guessGenerator);
        }
    }
}
