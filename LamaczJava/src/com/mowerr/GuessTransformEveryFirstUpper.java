package com.mowerr;

public class GuessTransformEveryFirstUpper implements IGuessTransform{
    @Override
    public String Transform(String... words) {
        StringBuilder sb = new StringBuilder();

        for(String word: words){
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1).toLowerCase());
        }

        return sb.toString();
    }
}
