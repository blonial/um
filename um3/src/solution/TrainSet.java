package solution;

import java.io.BufferedReader;
import java.io.Reader;

public class TrainSet {
    private final Sample[] samples;

    public TrainSet(Sample[] samples) {
        this.samples = samples;
    }

    public Sample[] getSamples() {
        return samples;
    }

    public static TrainSet readFromReader(Reader reader) {
        BufferedReader input = new BufferedReader(reader);
        Sample[] samples = input.lines().map(line -> new Sample(line.split(" "))).toArray(Sample[]::new);
        return new TrainSet(samples);
    }
}
