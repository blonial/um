package solution.regression;

public class PolygonFragment {
    private final int[] paramIndexes;
    private double factor;

    public PolygonFragment(int[] parameters, double factor) {
        this.paramIndexes = parameters;
        this.factor = factor;
    }

    public double getX(Vector vector) {
        double result = 1.0;
        for (Integer paramIndex : this.paramIndexes) {
            result *= vector.getValues()[paramIndex];
        }
        return result;
    }

    public double getY(Vector vector) {
        double result = this.factor;
        for (Integer paramIndex : this.paramIndexes) {
            result *= vector.getValues()[paramIndex];
        }
        return result;
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

    public boolean isZero() {
        for (int paramIndex : paramIndexes) {
            if (paramIndex != 0) {
                return false;
            }
        }
        return true;
    }
}
