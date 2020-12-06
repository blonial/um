package solution;

public class PolygonFragment {
    private final int[] paramIndexes;
    private double factor;

    public PolygonFragment(int[] parameters, double factor) {
        this.paramIndexes = parameters;
        this.factor = factor;
    }

    public int[] getParamIndexes() {
        return paramIndexes;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }
}
