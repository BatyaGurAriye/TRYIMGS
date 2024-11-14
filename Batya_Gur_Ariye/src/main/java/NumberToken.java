public class NumberToken extends Token {
    private double value;

    public NumberToken(String value) throws Exception {
        setValue(value);
    }

    public NumberToken(double value) throws Exception {
        this.value = value;
    }

    public void setValue(String value) throws Exception {
        try {
            this.value = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number format: " + value);
        }
    }


    public double getValue() {
        return this.value;
    }
}
