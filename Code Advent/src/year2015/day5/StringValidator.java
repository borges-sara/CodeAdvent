package year2015.day5;

import java.util.Objects;
import java.util.function.Predicate;

public class StringValidator {
    private static final Predicate<String> isNotNull =
            Objects::nonNull;

    private static final Predicate<String> contains3Vowels =
            s -> s.matches(".*(?:[aeiouAEIOU].*){3,}");

    private static final Predicate<String> containsDoubleLetter =
            s -> s.matches(".*([a-zA-Z])\\1.*");

    private static final Predicate<String> doesNotContainPredicate =
            s -> s.matches("^(?!.*(?:ab|cd|pq|xy)).*$");

    private static final Predicate<String> pairOfLetters =
            s -> s.matches(".*([a-z]{2}).*\\1.*");

    private static final Predicate<String> lettersThatRepeatWithInterval =
            s -> s.matches(".*([a-z]).\\1.*");

    private static final  Predicate<String> validator =
            isNotNull
                    .and(contains3Vowels)
                    .and(containsDoubleLetter)
                    .and(doesNotContainPredicate);

    private static final Predicate<String> validatorPart2 =
            isNotNull
                    .and(pairOfLetters)
                    .and(lettersThatRepeatWithInterval);


    public static boolean validate(String string){
        return validator.test(string);
    }

    public static boolean validatePart2(String string){
        return validatorPart2.test(string);
    }
}
