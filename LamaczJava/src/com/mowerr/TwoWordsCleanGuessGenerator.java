package com.mowerr;

import java.util.ArrayList;

public class TwoWordsCleanGuessGenerator implements IGuessGenerator {
    private IGuessTransform transform;
    private ArrayList<String> dictionary;

    private int currentFirstWordIndex = 0;
    private int currentSecondWordIndex = 0;

    TwoWordsCleanGuessGenerator(IGuessTransform transform){
        this.dictionary = GlobalData.getInstance().getDictionary();
        this.transform = transform;
    }

    @Override
    public String GetNextGuess() {
    	if(currentFirstWordIndex == dictionary.size())
    		return "";
    	
        String firstWord = dictionary.get(currentFirstWordIndex);
        String secondWord = dictionary.get(currentSecondWordIndex);
        
        String guess = transform.Transform(firstWord, secondWord);

        UpdateIndexValues();

        return guess;
    }


    protected void UpdateIndexValues() {
        currentSecondWordIndex++;

        if(currentSecondWordIndex >= dictionary.size()){
            currentSecondWordIndex = 0;
            currentFirstWordIndex++;
        }
    }
}
