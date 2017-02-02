package com.mowerr;

public class GuessNoAffixAplier implements IGuessAffixApplier{
    @Override
    public String ApplyAffixes(String word, int affixValue) {
        return word;
    }
}
