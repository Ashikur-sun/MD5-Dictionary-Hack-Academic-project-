package com.mowerr;

public class GuessTransformNoTransform extends GuessTransformWithJoin {
    @Override
    protected String TransformSingleString(String singleString) {
        return singleString;
    }
}
