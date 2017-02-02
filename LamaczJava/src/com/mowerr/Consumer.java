package com.mowerr;

public class Consumer implements Runnable{
    private int crackedPasswords = 0;
    private boolean isRunning = true;

    @Override
    public void run() {
        while(isRunning){
            synchronized (GlobalData.getInstance()){
                try {
                    GlobalData.getInstance().wait();
                    DisplayAllCrackedPasswords();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Stop(){
        this.isRunning = false;
        synchronized (GlobalData.getInstance()) {
            GlobalData.getInstance().notify();
        }
    }

    private void DisplayAllCrackedPasswords() {
        GlobalData globalData = GlobalData.getInstance();
        while(globalData.IsAnyCrackedPasswordLeft()){
            CrackedPasswordEntity crackedPassword = globalData.GetNextCrackedPassword();
            crackedPassword.GetEntity().setCracked();
            System.out.println(crackedPassword.GetOriginalPassword());
            crackedPasswords++;
        }

        CheckForFinish();
    }

    private void CheckForFinish() {
        GlobalData globalData = GlobalData.getInstance();
        if(globalData.getPasswordsDB().GetNumberOfPasswords() == crackedPasswords){
            globalData.setCrackingFinished(true);
            isRunning = false;
            System.out.println();
            System.out.println("### Lamanie hasel zakonczone! ###");
        }
    }

    public void DisplayProgress(){
        int allPasswords = GlobalData.getInstance().getPasswordsDB().GetNumberOfPasswords();
        int progress = crackedPasswords*100/allPasswords;
        System.out.println("Złamanych haseł: " + crackedPasswords + "/" +allPasswords + " (" + progress + "%)");
    }
    
    public void ResetProgress(){
    	crackedPasswords = 0;
    }
}
