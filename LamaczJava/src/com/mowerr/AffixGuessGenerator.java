package com.mowerr;

import java.util.ArrayList;

public class AffixGuessGenerator implements IGuessGenerator {
    private IGuessTransform transform;
    private IGuessAffixApplier affixApplier;
    private ArrayList<String> dictionary;

    private int currentWordIndex = 0;
    private int currentAffix = 0;

    AffixGuessGenerator(IGuessTransform transform, IGuessAffixApplier affixApplier){
        this.dictionary = GlobalData.getInstance().getDictionary();
        this.transform = transform;
        this.affixApplier = affixApplier;
    }

    @Override
    public String GetNextGuess() {
        String word = dictionary.get(currentWordIndex);
        word = transform.Transform(word);

        word = affixApplier.ApplyAffixes(word, currentAffix);

        UpdateIndexAndAffixValues();

        return word;
    }


    protected void UpdateIndexAndAffixValues() {
        currentWordIndex++;

        if(currentWordIndex >= dictionary.size()){
            currentWordIndex = 0;
            currentAffix++;
        }
    }
}
