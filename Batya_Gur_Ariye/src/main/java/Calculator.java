import java.io.Serializable;
import java.util.ArrayList;

public class Calculator {
    private final ExpressionParser parser = new ExpressionParser();
    private final Evaluator evaluator = new Evaluator();

    public double calculate(String expression) throws Exception {
        ArrayList<Token> parsedExpression = parser.parse(expression);
        return evaluator.evaluate(parsedExpression);
    }
}
