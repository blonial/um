package solution;

public class TestSample {
    private final Vector vector;
    private double result;

    public TestSample(String[] values) {
        this.vector = new Vector(values);
    }

    public Vector getVector() {
        return vector;
    }

    public void normalizeX(double[] min, double[] max) {
        for (int i = 0; i < min.length; i++) {
            this.vector.getValues()[i + 1] = this.getNormalizedValue(min[i], max[i], this.vector.getValues()[i + 1]);
        }
    }

    public void transformToOriginalY(double min, double max) {
        this.result = this.getOriginalValue(min, max, this.result);
    }

    private double getNormalizedValue(double min, double max, double value) {
        return 2 * (value - min) / (max - min) - 1;
    }

    private double getOriginalValue(double min, double max, double value) {
        return (((value + 1) * (max - min)) / 2) + min;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }
}
