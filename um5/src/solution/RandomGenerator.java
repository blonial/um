package solution;

import java.io.IOException;
import java.io.InputStreamReader;

public class RandomGenerator {
    public static void main(String[] args) throws IOException {
        GradeSet set = GradeSet.readSetFromFile(new InputStreamReader(System.in));
        set.saveSetToFile(args[1]);
    }
}
