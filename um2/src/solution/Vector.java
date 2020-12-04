package solution;

public class Vector {
    private final double[] values;

    public Vector(String[] values) {
        this.values = new double[values.length + 1];
        this.values[0] = 1.0;
        for (int i = 1; i < this.values.length; i++) {
            this.values[i] = Double.parseDouble(values[i - 1]);
        }
    }

    public double[] getValues() {
        return values;
    }
}
