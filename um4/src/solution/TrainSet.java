package solution;

import java.io.BufferedReader;
import java.io.Reader;

public class TrainSet {
    private final TrainSample[] samples;

    public TrainSet(TrainSample[] samples) {
        this.samples = samples;
    }

    public TrainSample[] getSamples() {
        return samples;
    }

    public void normalizeY(double min, double max) {
        for (TrainSample sample : this.samples) {
            sample.normalizeY(min, max);
        }
    }

    public void normalizeX(double[] min, double[] max) {
        for (TrainSample sample : this.samples) {
            sample.normalizeX(min, max);
        }
    }

    public void transformToOrginalY(double min, double max) {
        for (TrainSample sample : this.samples) {
            sample.transformToOriginalY(min, max);
        }
    }

    public void transformToOriginalX(double[] min, double[] max) {
        for (TrainSample sample : this.samples) {
            sample.transformToOriginalX(min, max);
        }
    }

    public double getMinY() {
        double min = this.samples[0].getExpectedValue();

        for (int i = 1; i < samples.length; i++) {
            min = Math.min(min, samples[i].getExpectedValue());
        }

        return min;
    }

    public double getMaxY() {
        double max = this.samples[0].getExpectedValue();

        for (int i = 1; i < samples.length; i++) {
            max = Math.max(max, samples[i].getExpectedValue());
        }

        return max;
    }


    public double[] getMinX() {
        int n = this.samples[0].getVector().getValues().length - 1;
        double[] minArr = new double[n];

        for (int i = 1; i <= n; i++) {
            double min = this.samples[0].getVector().getValues()[i];
            for (int j = 1; j < this.samples.length; j++) {
                min = Math.min(min, this.samples[j].getVector().getValues()[i]);
            }
            minArr[i - 1] = min;
        }

        return minArr;
    }

    public double[] getMaxX() {
        int n = this.samples[0].getVector().getValues().length - 1;
        double[] maxArr = new double[n];

        for (int i = 1; i <= n; i++) {
            double max = this.samples[0].getVector().getValues()[i];
            for (int j = 1; j < this.samples.length; j++) {
                max = Math.max(max, this.samples[j].getVector().getValues()[i]);
            }
            maxArr[i - 1] = max;
        }

        return maxArr;
    }

    public static TrainSet readFromReader(Reader reader) {
        BufferedReader input = new BufferedReader(reader);
        TrainSample[] trainSamples = input.lines()
                .map(line -> new TrainSample(line.split(" ")))
                .toArray(TrainSample[]::new);
        return new TrainSet(trainSamples);
    }
}
