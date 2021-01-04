package solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class GradeSet {
    private Grade[] grades;

    public GradeSet(Grade[] grades) {
        this.grades = grades;
    }

    public static GradeSet readSetFromFile(Reader reader) {
        BufferedReader input = new BufferedReader(reader);
        Grade[] grades = input.lines().map(line -> {
            String[] values = line.split(";");
            Grade grade = new Grade(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
            grade.setRandomGrade();
            return grade;
        }).toArray(Grade[]::new);

        return new GradeSet(grades);
    }

    public void saveSetToFile(String fileName) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        for(Grade grade: this.grades) {
            output.write(grade.getCSVLine());
            output.newLine();
        }
        output.flush();
    }
}
