package com.mowerr;

public class GuessTransformFirstUpper extends GuessTransformWithJoin {
    @Override
    public String TransformSingleString(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
