package com.mowerr;

public class GuessDoubleAffixApplier implements IGuessAffixApplier{

    @Override
    public String ApplyAffixes(String word, int affixValue) {
        return affixValue + word + affixValue;
    }
}
