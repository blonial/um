package solution;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        IntStream.Builder data = IntStream.builder();

        String line;
        while ((line = br.readLine()) != null) {
            data.add(Integer.parseInt(line));
        }

        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));
        data.build().sorted().forEach(out::println);
        out.flush();
    }
}
