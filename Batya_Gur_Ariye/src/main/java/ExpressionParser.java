import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    public ArrayList<Token> parse(String expression) throws Exception {
        expression = deleteSpaces(expression);
        ArrayList<Token> tokens = new ArrayList<>();
        logicalExerciseStructure(expression);
        containsOnlyNumbersAndOperators(expression);
        parseExpression(expression, tokens);
        return tokens;
    }

    private String deleteSpaces(String expression) {
        return expression.replace(" ", "");
    }

    private boolean logicalExerciseStructure(String expression) throws Exception {
        if (expression.matches("")) {
            return true;
        }
        if ((expression.matches(".*[+*/.]{2,}.*"))
                || (expression.matches("^[+*/.].*") || expression.matches(".*[+\\-*/.]$"))
        ) {
            throw new Exception("""
                    Incorrect format does not match calculation exercise.
                    try again""");
        }
        return true;
    }

    private boolean containsOnlyNumbersAndOperators(String expression) throws Exception {
        if (expression.matches("[0-9+\\-*/.]+") || expression.matches("")) {
            return true;
        }
        throw new Exception("The characters in the string do not match");
    }


    private void parseExpression(String expression, ArrayList<Token> expressionTokens) throws Exception {
        correctRegularExpression(expression);
        Matcher matcher = sanitizeExpression(expression);
        separationList(expressionTokens, matcher);
    }

    private String correctRegularExpression(String expression) throws Exception {
        expression = expression.replace("-+", "-");
        expression = expression.replace("--", "+");
        if (expression.contains("-*") || expression.contains("-/")) {
            throw new Exception("Invalid format");
        }
        return expression;
    }

    private Matcher sanitizeExpression(String expression) {
        String regex = "[+*/-]|-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(expression);
    }

    private void separationList(ArrayList<Token> expressionTokens, Matcher matcher) throws Exception {
        boolean lastWasOperator = true;
        while (matcher.find()) {
            String token = matcher.group();
            if (token.equals("-") && lastWasOperator && matcher.find()) {
                token += matcher.group();
            }
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                expressionTokens.add(new NumberToken(token));
                lastWasOperator = false;
            } else {
                expressionTokens.add(new OperatorToken(token.charAt(0)));
                lastWasOperator = true;
            }
        }
    }
}
