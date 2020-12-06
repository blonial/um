package solution;

public class LossFunction {
    private final TrainSet trainSet;
    private final Polygon polygon;

    public LossFunction(TrainSet trainSet, Polygon polygon) {
        this.trainSet = trainSet;
        this.polygon = polygon;
    }

    public double calculateLoss(int j) {
        double result = 0.0;
        for (Sample sample : trainSet.getSamples()) {
            result += this.calculate(sample, j);
        }
        return result / trainSet.getSamples().length;
    }

    private double calculate(Sample sample, int j) {
        return (this.polygon.calculate(sample.getVector()) - sample.getExpectedValue()) * sample.getVector().getValues()[j];
    }
}
