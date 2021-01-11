package solution;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int k = 16;

        MovieSet movieSet = MovieSet.readSetFromFile(new FileReader("moviesData.txt"));
        GradeSet trainSet = GradeSet.readSetFromFile(new FileReader("train.csv"), true);
        GradeSet taskSet = GradeSet.readSetFromFile(new FileReader("task.csv"), false);

        KNN knn = new KNN(trainSet, movieSet);
        for(Grade grade : taskSet.getGrades()) {
            grade.setGrade(knn.predictValue(grade, k));
        }

        taskSet.saveSetToFile("submission.csv");
    }
}
