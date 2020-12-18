package solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CrossValidation {
    private final TrainSet trainSet;
    private final TrainSet validationSet;

    public CrossValidation(TrainSet trainSet, int m) {
        List<TrainSample> samples = Arrays.asList(trainSet.getSamples());
        Collections.shuffle(samples);
        int validationSetSize = samples.size() / m;
        this.validationSet = new TrainSet(samples.subList(0, validationSetSize).toArray(new TrainSample[0]));
        this.trainSet = new TrainSet(samples.subList(validationSetSize + 1, samples.size() - 1).toArray(new TrainSample[0]));
    }

    public TrainSet getTrainSet() {
        return trainSet;
    }

    public TrainSet getValidationSet() {
        return validationSet;
    }
}
