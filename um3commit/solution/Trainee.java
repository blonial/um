package solution;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.DoubleStream;

public class Trainee {

    public static void main(String[] args) throws IOException {
        Polygon polygon = Polygon.readFromReader(new BufferedReader(new FileReader(args[1])));

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        DoubleStream.Builder output = DoubleStream.builder();
        in.lines().forEach(input -> {
            output.add(polygon.calculate(new Vector((input + " 0").split(" "))));
        });

        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));
        output.build().forEach(out::println);
        out.flush();
    }
}
