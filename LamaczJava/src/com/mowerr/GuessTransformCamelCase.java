package com.mowerr;

public class GuessTransformCamelCase implements IGuessTransform{
    @Override
    public String Transform(String... words) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for(String word: words){
            if(first) {
                sb.append(word.toLowerCase());
                first = false;
            } else{
                sb.append(word.substring(0,1).toUpperCase());
                sb.append(word.substring(1).toLowerCase());
            }
        }

        return sb.toString();
    }
}
