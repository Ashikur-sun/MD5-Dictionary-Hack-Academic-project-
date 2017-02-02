package com.mowerr;

public abstract class GuessTransformWithJoin implements IGuessTransform{
    @Override
    public String Transform(String... words) {
        String singleString = JoinWordsTogether(words);
        return TransformSingleString(singleString);
    }

    protected abstract String TransformSingleString(String singleString);

    private String JoinWordsTogether(String... words){
        StringBuilder sb = new StringBuilder();
        for(String word: words){
            sb.append(word);
        }
        return sb.toString();
    }
}
