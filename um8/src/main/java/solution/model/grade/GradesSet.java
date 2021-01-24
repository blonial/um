package solution.model.grade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class GradesSet {
    private final Grade[] grades;

    public GradesSet(Grade[] grades) {
        this.grades = grades;
    }

    public Grade[] getGrades() {
        return grades;
    }

    public GradesSet getGradesByUserId(int userId) {
        List<Grade> userGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getUserId() == userId) {
                userGrades.add(grade);
            }
        }

        return new GradesSet(userGrades.toArray(Grade[]::new));
    }

    public GradesSet getGradesByMovieId(int movieId) {
        List<Grade> userGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getMovieId() == movieId) {
                userGrades.add(grade);
            }
        }

        return new GradesSet(userGrades.toArray(Grade[]::new));
    }

    public Integer[] getUsersIds() {
        List<Integer> userIds = new ArrayList<>();
        for (Grade grade : grades) {
            if (!userIds.contains(grade.getUserId())) {
                userIds.add(grade.getUserId());
            }
        }
        return userIds.toArray(Integer[]::new);
    }

    public Integer[] getMoviesIds() {
        List<Integer> moviesIds = new ArrayList<>();
        for (Grade grade : grades) {
            if (!moviesIds.contains(grade.getMovieId())) {
                moviesIds.add(grade.getMovieId());
            }
        }
        return moviesIds.toArray(Integer[]::new);
    }

    public static GradesSet readSetFromFile(Reader reader, boolean withGrade) {
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

        return new GradesSet(grades);
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
