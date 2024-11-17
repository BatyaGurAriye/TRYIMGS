import java.util.ArrayList;

public class Calculator {
    private final ExpressionParser parser = new ExpressionParser();
    private final Evaluator evaluator = new Evaluator();

    public double calculate(String expression) throws Exception {
        ArrayList<Token> parsedExpression = parser.convertString2Array(expression);
        return evaluator.calculationAnExerciseArray(parsedExpression);
    }

}