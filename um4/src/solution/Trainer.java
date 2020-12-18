package solution;

import java.util.Arrays;

public class Trainer {

    private final Polygon polygon;

    public Trainer(Polygon polygon) {
        this.polygon = polygon;
    }

    public void train(TrainSet trainSet) {
        final LossFunction lossFunction = new LossFunction(this.polygon);

        final double learningRate = 0.1;
        final double breakStepSize = 0.0000001;
        final double maxIterations = 100000;

        int realIterations = 0;

        while (realIterations < maxIterations) {
            double loss = 0;
            double[] newFactors = new double[polygon.getPolygonParams().length];
            for (int i = 0; i < polygon.getPolygonParams().length; i++) {
                double stepSize = lossFunction.calculateLoss(polygon.getPolygonParams()[i], trainSet) * learningRate;
                loss = Math.max(loss, Math.abs(stepSize));
                newFactors[i] = polygon.getPolygonParams()[i].getFactor() - stepSize;
            }
            polygon.applyNewFactors(newFactors);
            realIterations++;
            if (loss < breakStepSize) {
                break;
            }
        }
    }

    public double calculateMeanSquareError(TrainSet trainSet) {
        double sum = Arrays.stream(trainSet.getSamples()).mapToDouble(sample -> {
            double value = sample.getExpectedValue() - this.polygon.calculate(sample.getVector());
            return value * value;
        }).sum();
        return sum / trainSet.getSamples().length;
    }
}
