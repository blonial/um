package solution;

public class Sample {
    private final Vector vector;
    private final double expectedValue;

    public Sample(String[] values) {
        this.vector = new Vector(values);
        this.expectedValue = Double.parseDouble(values[values.length - 1]);
    }

    public Vector getVector() {
        return vector;
    }

    public double getExpectedValue() {
        return expectedValue;
    }
}
