package com.mowerr;

import java.util.ArrayList;

public class CleanGuessGenerator implements IGuessGenerator {
	    private IGuessTransform transform;
	    private ArrayList<String> dictionary;

	    private int currentWordIndex = 0;

	    CleanGuessGenerator(IGuessTransform transform){
	        this.dictionary = GlobalData.getInstance().getDictionary();
	        this.transform = transform;
	    }

	    @Override
	    public String GetNextGuess() {
	    	if(currentWordIndex == dictionary.size())
	    		return "";
	    	
	        String word = dictionary.get(currentWordIndex);
	        word = transform.Transform(word);

	        UpdateIndexValues();

	        return word;
	    }


	    protected void UpdateIndexValues() {
	        currentWordIndex++;
	    }
}
