package solution;

public class Pair {
    private final FeatureVector featureVector;
    private final double distance;

    public Pair(FeatureVector featureVector, double distance) {
        this.featureVector = featureVector;
        this.distance = distance;
    }

    public FeatureVector getFeatureVector() {
        return featureVector;
    }

    public double getDistance() {
        return distance;
    }
}
