package com.mowerr;

public class SuffixAffixApplier implements IGuessAffixApplier{

    @Override
    public String ApplyAffixes(String word, int affixValue) {
        return word + affixValue;
    }
}
