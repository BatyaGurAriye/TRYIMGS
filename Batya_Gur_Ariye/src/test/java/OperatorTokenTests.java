import org.junit.Assert;
import org.junit.Test;

public class OperatorTokenTests {

    @Test
    public void testApply() {
        try {
            OperatorToken help = new OperatorToken('-');
            Assert.assertEquals(4.5, help.apply(17, 12.4), 0.1);
            help.setOperator('/');
            Assert.assertEquals(92.3, help.apply(876.85, 9.5), 0.5);
            help.setOperator('/');
            Assert.assertEquals(0, help.apply(1, 0), 0.5);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
