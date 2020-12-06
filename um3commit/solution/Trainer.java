package solution;

import java.io.*;

public class Trainer {

    public static void main(String[] args) throws IOException {
        Polygon polygon = Polygon.readFromReader(new InputStreamReader(System.in));
        TrainSet trainSet = TrainSet.readFromReader(new FileReader(args[1]));

        BufferedReader data_in = new BufferedReader(new FileReader(args[3]));
        int iterations = Integer.parseInt(data_in.readLine().substring(11));

        LossFunction lossFunction = new LossFunction(trainSet, polygon);

        double learningRate = 0.1;
        double breakStepSize = 0.00001;

        int realIterations = 0;
        while (realIterations < iterations) {
            double maxStepSize = 0.0;
            double[] newFactors = new double[polygon.getPolygonParams().length];
            for (int i = 0; i < polygon.getPolygonParams().length; i++) {
                double stepSize = lossFunction.calculateLoss(polygon.getPolygonParams()[i].getParamIndexes()[0]) * learningRate;
                maxStepSize = Math.max(maxStepSize, Math.abs(stepSize));
                newFactors[i] = polygon.getPolygonParams()[i].getFactor() - stepSize;
            }
            polygon.applyNewFactors(newFactors);
            realIterations++;
            if (maxStepSize < breakStepSize) {
                break;
            }
        }

        BufferedWriter data_out = new BufferedWriter(new FileWriter(args[5]));
        data_out.write("iterations=" + realIterations);
        data_out.flush();

        polygon.printToStream(System.out);
    }
}
