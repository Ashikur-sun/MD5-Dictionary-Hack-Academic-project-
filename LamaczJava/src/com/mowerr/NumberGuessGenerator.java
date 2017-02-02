package com.mowerr;

public class NumberGuessGenerator implements IGuessGenerator{
    private long i = -1;

    @Override
    public String GetNextGuess() {
        i++;
        return String.valueOf(i);
    }
}
