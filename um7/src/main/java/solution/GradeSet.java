package solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GradeSet {
    private final Grade[] grades;

    public GradeSet(Grade[] grades) {
        this.grades = grades;
    }

    public Grade[] getGrades() {
        return grades;
    }

    public GradeSet getGradesByUserId(int userId) {
        List<Grade> userGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getUserId() == userId) {
                userGrades.add(grade);
            }
        }

        return new GradeSet(userGrades.toArray(Grade[]::new));
    }

    public GradeSet getGradesByMovieId(int movieId) {
        List<Grade> userGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getMovieId() == movieId) {
                userGrades.add(grade);
            }
        }

        return new GradeSet(userGrades.toArray(Grade[]::new));
    }

    public static GradeSet readSetFromFile(Reader reader, boolean withGrade) {
        BufferedReader input = new BufferedReader(reader);
        Grade[] grades = input.lines().map(line -> {
            String[] values = line.split(";");
            return withGrade ? new Grade(Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    Integer.parseInt(values[2]),
                    Integer.parseInt(values[3])) : new Grade(Integer.parseInt(values[0]),
                    Integer.parseInt(values[1]),
                    Integer.parseInt(values[2]));
        }).toArray(Grade[]::new);

        return new GradeSet(grades);
    }

    public void saveSetToFile(String fileName) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
        for (Grade grade : this.grades) {
            output.write(grade.getCSVLine());
            output.newLine();
        }
        output.flush();
    }
}
