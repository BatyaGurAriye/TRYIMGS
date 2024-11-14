import java.util.HashSet;
import java.util.Set;

public class OperatorToken extends Token {

    private char operator;

    public OperatorToken(char operator) throws Exception {
        setOperator(operator);
    }

    public void setOperator(char operator) throws Exception {
        if (new HashSet<>(Set.of('+', '-', '/', '*')).contains(operator)) {
            this.operator = operator;
        } else {
            throw new Exception("Insert a valid operator");
        }
    }

    public double apply(double left, double right) {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public char getValue() {
        return this.operator;
    }
}
