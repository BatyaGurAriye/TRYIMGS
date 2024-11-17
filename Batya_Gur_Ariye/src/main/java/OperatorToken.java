import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class OperatorToken extends Token {

    private char operator;
    private static final Set<Character> VALID_OPERATORS = Set.of('+', '-', '/', '*');
    private static final Map<Character, BiFunction<Double, Double, Double>> OPERATOR_FUNCTIONS = new HashMap<>();

    static {
        OPERATOR_FUNCTIONS.put('+', (left, right) -> left + right);
        OPERATOR_FUNCTIONS.put('-', (left, right) -> left - right);
        OPERATOR_FUNCTIONS.put('*', (left, right) -> left * right);
        OPERATOR_FUNCTIONS.put('/', (left, right) -> {
            if (right == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return left / right;
        });
    }

    public OperatorToken(char operator) throws Exception {
        setOperator(operator);
    }

    public void setOperator(char operator) throws Exception {
        if (VALID_OPERATORS.contains(operator)) {
            this.operator = operator;
        } else {
            throw new Exception("Insert a valid operator");
        }
    }

    public char getValue() {
        return this.operator;
    }

    public double apply(double left, double right) {
        BiFunction<Double, Double, Double> operation = OPERATOR_FUNCTIONS.get(operator);
        if (operation == null) {
            throw new IllegalArgumentException("Unknown operator: " + operator);
        }
        return operation.apply(left, right);
    }

}
