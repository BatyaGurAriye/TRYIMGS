import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EvaluatorTests {

    @Test
    public void testEvaluate() throws Exception {
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new NumberToken(5));
        tokens.add(new OperatorToken('*'));
        tokens.add(new NumberToken(2.8));
        tokens.add(new OperatorToken('-'));
        tokens.add(new NumberToken(-1.5));

        Evaluator help = new Evaluator();
        assertEquals(15.5, help.evaluate(tokens), 0.1);

        tokens = new ArrayList<Token>();
        tokens.add(new NumberToken(.5));
        tokens.add(new OperatorToken('-'));
        tokens.add(new NumberToken(2.8));
        tokens.add(new OperatorToken('/'));
        tokens.add(new NumberToken(-1.4));
        assertEquals(2.5, help.evaluate(tokens), 0.1);

    }
}
