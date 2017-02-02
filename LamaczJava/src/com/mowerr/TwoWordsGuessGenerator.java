package com.mowerr;

import java.util.ArrayList;

public class TwoWordsGuessGenerator implements IGuessGenerator {
    private IGuessTransform transform;
    private IGuessAffixApplier affixApplier;
    private ArrayList<String> dictionary;

    private int currentFirstWordIndex = 0;
    private int currentSecondWordIndex = 0;
    private int currentAffix = -1;

    TwoWordsGuessGenerator(IGuessTransform transform, IGuessAffixApplier affixApplier){
        this.dictionary = GlobalData.getInstance().getDictionary();
        this.transform = transform;
        this.affixApplier = affixApplier;
    }

    @Override
    public String GetNextGuess() {
        String firstWord = dictionary.get(currentFirstWordIndex);
        String secondWord = dictionary.get(currentSecondWordIndex);

        String guess = transform.Transform(firstWord, secondWord);

        if(AffixShouldBeApplied())
            guess = affixApplier.ApplyAffixes(guess, currentAffix);

        UpdateIndexAndAffixValues();

        return guess;
    }

    protected boolean AffixShouldBeApplied() {
        return currentAffix >= 0;
    }


    protected void UpdateIndexAndAffixValues() {
        currentSecondWordIndex++;

        if(currentSecondWordIndex >= dictionary.size()){
            currentSecondWordIndex = 0;
            currentFirstWordIndex++;
        }

        if(currentFirstWordIndex >= dictionary.size()){
            currentFirstWordIndex = 0;
            currentAffix++;
        }
    }
}