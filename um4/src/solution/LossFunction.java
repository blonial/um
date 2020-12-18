package solution;

public class LossFunction {
    private final Polygon polygon;

    public LossFunction(Polygon polygon) {
        this.polygon = polygon;
    }

    public double calculateLoss(PolygonFragment fragment, TrainSet trainSet) {
        double result = 0.0;

        for (TrainSample trainSample : trainSet.getSamples()) {
            result += this.calculate(trainSample, fragment);
        }

        return result / trainSet.getSamples().length;
    }

    private double calculate(TrainSample trainSample, PolygonFragment fragment) {
        return (this.polygon.calculate(trainSample.getVector()) - trainSample.getExpectedValue()) * fragment.getX(trainSample.getVector());
    }
}
