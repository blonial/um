package solution;

import java.io.*;
import java.util.Arrays;

public class Regressor {

    public static void main(String[] args) throws FileNotFoundException {
        TrainSet trainSet = TrainSet.readFromReader(new FileReader(args[1]));

        final int maxPolygonDegree = 10;
        int polygonDegree = 2;

        final int n = trainSet.getSamples()[0].getVector().getValues().length - 1;

        Polygon bestPolygon = new Polygon(n, polygonDegree);
        double bestPolygonLoss = -1;

        final double[] minX = trainSet.getMinX();
        final double[] maxX = trainSet.getMaxX();
        final double minY = trainSet.getMinY();
        final double maxY = trainSet.getMaxY();

        trainSet.normalizeX(minX, maxX);
        trainSet.normalizeY(minY, maxY);

        while (polygonDegree <= maxPolygonDegree) {
            Polygon polygon = new Polygon(n, polygonDegree);

            Trainer trainer = new Trainer(polygon);
            CrossValidation cv = new CrossValidation(trainSet, 4);
            trainer.train(cv.getTrainSet());

            double polygonLoss = trainer.calculateMeanSquareError(cv.getValidationSet());

            if (bestPolygonLoss < 0 || bestPolygonLoss > polygonLoss) {
                bestPolygon = polygon;
                bestPolygonLoss = polygonLoss;
            }
            polygonDegree++;
        }

        Polygon finalBestPolygon = new Polygon(bestPolygon.getN(), bestPolygon.getK());
        Trainer trainer = new Trainer(finalBestPolygon);
        trainer.train(trainSet);

        TestSet testSet = TestSet.readFromReader(new InputStreamReader(System.in));
        PrintStream output = new PrintStream(new BufferedOutputStream(System.out));

        testSet.normalizeX(minX, maxX);

        Arrays.stream(testSet.getSamples())
                .mapToDouble(testSample -> {
                    testSample.setResult(finalBestPolygon.calculate(testSample.getVector()));
                    testSample.transformToOriginalY(minY, maxY);
                    return testSample.getResult();
                })
                .forEach(output::println);
        output.flush();
    }
}
