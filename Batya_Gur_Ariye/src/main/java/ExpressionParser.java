import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    public ArrayList<Token> parse(String expression) throws Exception {
        expression = deleteSpaces(expression);
        ArrayList<Token> tokens = new ArrayList<>();
        if (logicalExerciseStructure(expression)
                && containsOnlyNumbersAndOperators(expression))
            parseExpression(expression, tokens);

        return tokens;
    }

    private void parseExpression(String expression, ArrayList<Token> expressionTokens) throws Exception {
        expression = correctRegularExpression(expression);
        Matcher matcher = sanitizeExpression(expression);
        separationList(expressionTokens, matcher);
    }

    private void separationList(ArrayList<Token> expressionTokens, Matcher matcher) throws Exception {
        boolean lastWasOperator = true;
        while (matcher.find()) {
            String token = matcher.group();
            if (token.equals("-") && lastWasOperator) {
                if (matcher.find()) {
                    token += matcher.group();
                }
            }
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                expressionTokens.add(new NumberToken(token));
            } else {
                expressionTokens.add(new OperatorToken(token.charAt(0)));
            }
            lastWasOperator = token.matches("[+*/-]") && !token.matches("-\\d+(\\.\\d+)?");
        }
    }

    private String correctRegularExpression(String expression) {
        expression = expression.replace("-+", "-");
        expression = expression.replace("-*", "*-");
        expression = expression.replace("--", "+");
        expression = expression.replace("-/", "/-");
        return expression;
    }

    private Matcher sanitizeExpression(String expression) {
        String regex = "[+*/-]|-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(expression);
    }

    private boolean containsOnlyNumbersAndOperators(String expression) throws Exception {
        if (expression.matches("[0-9+\\-*/.]+") || expression.matches("")) {
            return true;
        }
        throw new Exception("The characters in the string do not match");
    }

    private boolean logicalExerciseStructure(String expression) throws Exception {
        if (expression.matches("")) {
            return true;
        }
        else if ((expression.matches(".*[+*/.]{2,}.*"))
                ||
                (expression.matches("^[+*/.].*") || expression.matches(".*[+\\-*/.]$"))
        )
            throw new Exception("Incorrect format does not match calculation exercise.\n" + "try again");
        else
            return true;
    }

    private String deleteSpaces(String expression) {
        return expression.replace(" ", "");
    }
}
