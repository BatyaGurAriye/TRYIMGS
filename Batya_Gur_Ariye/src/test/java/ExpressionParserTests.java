import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class ExpressionParserTests {
    @Test
    public void testParse() throws Exception {
        ExpressionParser ep = new ExpressionParser();

        assertParsedValues(ep.convertString2Array("1+48/12"), Arrays.asList(1.0, '+', 48.0, '/', 12.0));
        assertThrows(Exception.class, () -> ep.convertString2Array(".1+48.5/12."));
        assertThrows(Exception.class, () -> ep.convertString2Array("1.+48.5/-12."));
        assertThrows(Exception.class, () -> ep.convertString2Array("-1+48.5/12."));
    }

    private void assertParsedValues(List<Token> tokens, List<Object> expectedValues) {
        List<Object> actualValues = tokens.stream()
                .map(token -> {
                    if (token instanceof NumberToken) {
                        return ((NumberToken) token).getValue();
                    }
                    if (token instanceof OperatorToken) {
                        return ((OperatorToken) token).getValue();
                    } else {
                        throw new IllegalArgumentException("Unknown token type");
                    }
                })
                .collect(Collectors.toList());
        assertEquals(expectedValues, actualValues);
    }
}
