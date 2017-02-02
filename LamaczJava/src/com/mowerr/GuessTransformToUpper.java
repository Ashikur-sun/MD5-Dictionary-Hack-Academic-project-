package com.mowerr;

public class GuessTransformToUpper extends GuessTransformWithJoin{
    @Override
    public String TransformSingleString(String word) {
        return word.toUpperCase();
    }
}
