import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberTokenTests {
    @Test
    public void testSetValue() throws Exception {
        NumberToken help = new NumberToken("5");
        assertEquals(5.0, help.getValue(), 0.1);
        help.setValue("-.1");
        assertEquals(-0.1, help.getValue(), 0.1);

    }
}
