package solution;

public class TrainSample {
    private final Vector vector;
    private double expectedValue;

    public TrainSample(String[] values) {
        this.vector = new Vector(values);
        this.expectedValue = Double.parseDouble(values[values.length - 1]);
    }

    public void normalizeY(double min, double max) {
        this.expectedValue = this.getNormalizedValue(min, max, this.expectedValue);
    }

    public void normalizeX(double[] min, double[] max) {
        for (int i = 0; i < min.length; i++) {
            this.vector.getValues()[i + 1] = this.getNormalizedValue(min[i], max[i], this.vector.getValues()[i + 1]);
        }
    }

    public void transformToOriginalY(double min, double max) {
        this.expectedValue = this.getOriginalValue(min, max, this.expectedValue);
    }

    public void transformToOriginalX(double[] min, double[] max) {
        for (int i = 0; i < min.length; i++) {
            this.vector.getValues()[i + 1] = this.getOriginalValue(min[i], max[i], this.vector.getValues()[i + 1]);
        }
    }

    private double getNormalizedValue(double min, double max, double value) {
        return 2 * (value - min) / (max - min) - 1;
    }

    private double getOriginalValue(double min, double max, double value) {
        return (((value + 1) * (max - min)) / 2) + min;
    }

    public Vector getVector() {
        return vector;
    }

    public double getExpectedValue() {
        return expectedValue;
    }
}
