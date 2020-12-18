package solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Generator {

    public static void main(String[] args) throws IOException {
        Polygon polygon = Polygon.readFromReader(new InputStreamReader(System.in));

        BufferedReader input = new BufferedReader(new FileReader(args[1]));
        int trainSet = Integer.parseInt(input.readLine().substring(9));
        int testSet = Integer.parseInt(input.readLine().substring(8));

        String[] startStrings = input.readLine().substring(6).split(" ");
        String[] stopStrings = input.readLine().substring(5).split(" ");

        double[] start = new double[startStrings.length];
        double[] stop = new double[stopStrings.length];

        for (int i = 0; i < start.length; i++) {
            start[i] = Double.parseDouble(startStrings[i]);
            stop[i] = Double.parseDouble(stopStrings[i]);
        }

        BufferedWriter set = new BufferedWriter(new FileWriter(args[3]));

        Random r = new Random();

        for (int i = 0; i < trainSet; i++) {
            StringBuilder vector = new StringBuilder();
            for (int j = 0; j < start.length; j++) {
                vector.append(start[j] + (stop[j] - start[j]) * r.nextDouble());
                vector.append(" ");
            }
            set.write(vector.toString());
            vector.append(0);
            set.write(Double.toString(polygon.calculate(new Vector(vector.toString().split(" ")))));
            if (i != trainSet - 1) {
                set.newLine();
            }
        }

        set.flush();

        BufferedWriter test = new BufferedWriter(new FileWriter(args[5]));

        for (int i = 0; i < testSet; i++) {
            StringBuilder vector = new StringBuilder();
            for (int j = 0; j < start.length; j++) {
                vector.append(start[j] + (stop[j] - start[j]) * r.nextDouble());
                if (j != start.length - 1) {
                    vector.append(" ");
                }
            }
            test.write(vector.toString());
            if (i != testSet - 1) {
                test.newLine();
            }
        }

        test.flush();
    }
}
