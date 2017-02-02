package com.mowerr;

public class Factory {
    public static IGuessAffixApplier affixApplier(AffixApplier affixApplier){
        switch(affixApplier){
            case doubleAffix:
                return new GuessDoubleAffixApplier();

            case noAffix:
                return new GuessNoAffixAplier();

            case prefix:
                return new PrefixAffixAplier();

            case suffix:
                return new SuffixAffixApplier();

            default:
                return null;
        }
    }

    public static IGuessTransform transform(Transform transform){
        switch(transform){
            case toUpper:
                return new GuessTransformToUpper();

            case toLower:
                return new GuessTransformToLower();

            case firstUpper:
                return new GuessTransformFirstUpper();

            case camelCase:
                return new GuessTransformCamelCase();

            case noTransform:
                return new GuessTransformNoTransform();

            case everyFirstUpper:
                return new GuessTransformEveryFirstUpper();

            default:
                return null;
        }
    }

    public static IGuessGenerator guessGenerator(GuessType guessType, IGuessTransform transform, IGuessAffixApplier affixApplier){
        switch(guessType){
            case singleWord:
                return new AffixGuessGenerator(transform, affixApplier);

            case twoWords:
                return new TwoWordsGuessGenerator(transform, affixApplier);

            case numbers:
                return new NumberGuessGenerator();

            case clean:
            	return new CleanGuessGenerator(transform);
            	
            case cleanTwoWords:
            	return new TwoWordsCleanGuessGenerator(transform);
                
            default:
                return null;
        }
    }
}
