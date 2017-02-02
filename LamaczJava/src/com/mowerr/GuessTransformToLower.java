package com.mowerr;

public class GuessTransformToLower extends GuessTransformWithJoin{
    @Override
    public String TransformSingleString(String word) {
        return word.toLowerCase();
    }
}
