package solution.model;

import solution.regression.Polygon;

public class RegressionObject {
    private final int id;
    protected Polygon polygon;
    private double[] newFactorsToAdd;

    public RegressionObject(int id) {
        this.id = id;
    }

    public void addToNewFactorsToAdd(double[] newFactorsToAdd) {
        if (this.newFactorsToAdd == null) {
            this.newFactorsToAdd = newFactorsToAdd;
        } else {
            for (int i = 0; i < newFactorsToAdd.length; i++) {
                this.newFactorsToAdd[i] += newFactorsToAdd[i];
            }
        }
    }

    public double applyNewFactors(double learningRate) {
        double loss = polygon.applyNewFactors(newFactorsToAdd, learningRate);
        newFactorsToAdd = null;
        return loss;
    }

    public int getId() {
        return id;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
