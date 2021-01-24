package solution.regression;

public class Vector {
    private final double[] values;

    public Vector(String[] values) {
        this.values = new double[values.length];
        this.values[0] = 1.0;
        for (int i = 1; i < this.values.length; i++) {
            this.values[i] = Double.parseDouble(values[i - 1]);
        }
    }

    public Vector(Polygon polygon) {
        this.values = new double[polygon.getPolygonParams().length + 1];
        this.values[0] = 1.0;
        for (int i = 0; i < polygon.getPolygonParams().length; i++) {
            this.values[i + 1] = polygon.getPolygonParams()[i].getFactor();
        }
    }

    public double[] getValues() {
        return values;
    }
}
