package Exercises.ex4;

import java.util.regex.Pattern;

public class RegexExercise {
    public static void main(String[] args) {
        // starts with "zhe"
        testRegex("Starts with cat", "^zhe.*", "zhezhang", "zhangzhe");
        // ends with "dog"
        testRegex("Ends with dog", ".*dog$", "hotdog", "dogfood");
        // only lowercase letters
        testRegex("Only lowercase letters", "[a-z]+", "helloworld", "helloWorld");
        // only digits
        testRegex("Only digits", "\\d+", "12345", "12345A");
        // yes or no
        testRegex("Either yes or no", "yes|no", "yes", "maybe");
        // exactly 3 digits
        testRegex("Exactly 3 digits", "\\d{3}", "456", "45");
        // word characters only
        testRegex("Word characters only", "\\w+", "user_123", "user-123");
    }

    public static void testRegex(String patternName, String regex, String positiveCase, String negativeCase) {
        System.out.println("The test cases");
        System.out.println("Pattern Name: " + patternName);
        System.out.println("Regex: " + regex);
        boolean positiveResult = Pattern.matches(regex, positiveCase);
        boolean negativeResult = Pattern.matches(regex, negativeCase);
        System.out.println("Positive case: \"" + positiveCase + "\" -> " + positiveResult);
        System.out.println("Negative case: \"" + negativeCase + "\" -> " + negativeResult);
        System.out.println();
    }
}