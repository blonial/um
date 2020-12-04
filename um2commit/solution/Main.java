package solution;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.DoubleStream;

public class Main {

    public static void main(String[] args) throws IOException {
        // building polynomial
        BufferedReader description = new BufferedReader(new FileReader(args[1]));
        // reading n and k
        description.readLine();
        // creating polygon
        Double[][] polygon = description.lines().map(line -> {
            String[] values = line.split(" ");
            Double[] mappedValues = new Double[values.length];
            for (int i = 0; i < mappedValues.length; i++) {
                mappedValues[i] = Double.parseDouble(values[i]);
            }
            return mappedValues;
        }).toArray(Double[][]::new);

        // reading in
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // defining output
        DoubleStream.Builder output = DoubleStream.builder();
        // calculating output
        in.lines().forEach(input -> {
            // splitting values
            String[] values = input.split(" ");
            // mapped values
            Double[] mappedValues = new Double[values.length + 1];
            // first is 1.0
            mappedValues[0] = 1.0;
            // mapping others values starting from index 1
            for (int i = 1; i < mappedValues.length; i++) {
                mappedValues[i] = Double.parseDouble(values[i - 1]);
            }
            // calculating result
            double result = 0.0;
            // for per factors
            for (Double[] arg : polygon) {
                // factor
                double partResult = arg[arg.length - 1];
                // args
                for (int i = 0; i < arg.length - 1; i++) {
                    partResult *= mappedValues[arg[i].intValue()];
                }
                result += partResult;
            }
            // add output
            output.add(result);
        });

        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));
        output.build().forEach(out::println);
        out.flush();
    }
}
