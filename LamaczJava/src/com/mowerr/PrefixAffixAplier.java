package com.mowerr;

public class PrefixAffixAplier implements IGuessAffixApplier {
    @Override
    public String ApplyAffixes(String word, int affixValue) {
        return affixValue + word;
    }
}
