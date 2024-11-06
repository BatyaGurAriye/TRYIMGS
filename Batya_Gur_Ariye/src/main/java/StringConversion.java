import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConversion {
    public static String deleteSpaces(String input) {
        return input.replace(" ", "");
    }

    public static boolean containsOnlyNumbersAndOperators(String s) throws Exception {
        if (s.matches("[0-9+\\-*/.]+")) {
            return true;
        }
        throw new Exception("The characters in the string do not match");
    }

    public static boolean logicalExerciseStructure(String s) throws Exception {
        if ((s.matches(".*[+*/.]{2,}.*")) ||
                (s.matches("^[+*/.].*") || s.matches(".*[+\\-*/.]$"))
        )
            throw new Exception("Incorrect format does not match calculation exercise.\n" + "try again");
        return true;
    }

    public static String stringCorrection(String input) {
        input = input.replace("-+", "-");
        input = input.replace("-*", "*-");
        input = input.replace("--", "+");
        input = input.replace("-/", "/-");
        return input;
    }

    public static Matcher sanitizeExpression(String input) {
        String regex = "[+*/-]|-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public static ArrayList<Object> stringCutting(String input) {
        Matcher matcher = sanitizeExpression(stringCorrection(input));
        ArrayList<Object> exercise = new ArrayList<>();
        boolean lastWasOperator = true;
        while (matcher.find()) {
            String token = matcher.group();
            if (token.equals("-") && lastWasOperator) {
                if (matcher.find()) {
                    token += matcher.group();
                }
            }
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                exercise.add(Double.parseDouble(token));
            } else {
                exercise.add(token.charAt(0));
            }
            lastWasOperator = token.matches("[+*/-]") && !token.matches("-\\d+(\\.\\d+)?");
        }
        return exercise;
    }

    public static ArrayList<Object> correctString(String input) throws Exception {
        input = deleteSpaces(input);
        if (logicalExerciseStructure(input) && containsOnlyNumbersAndOperators(input))
            return stringCutting(input);
        return new ArrayList<>();
    }
}