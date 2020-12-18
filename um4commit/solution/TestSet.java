package solution;

import java.io.BufferedReader;
import java.io.Reader;

public class TestSet {
    private final TestSample[] samples;

    public TestSet(TestSample[] samples) {
        this.samples = samples;
    }

    public TestSample[] getSamples() {
        return samples;
    }

    public void normalizeX(double[] min, double[] max) {
        for (TestSample sample : this.samples) {
            sample.normalizeX(min, max);
        }
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

    public static TestSet readFromReader(Reader reader) {
        BufferedReader input = new BufferedReader(reader);
        TestSample[] testSamples = input.lines().map(line -> new TestSample((line + " 0").split(" "))).toArray(TestSample[]::new);
        return new TestSet(testSamples);
    }
}
