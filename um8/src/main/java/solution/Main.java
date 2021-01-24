package solution;

import solution.model.grade.Grade;
import solution.model.grade.GradesSet;
import solution.model.movie.Movie;
import solution.model.movie.MoviesSet;
import solution.model.user.User;
import solution.model.user.UsersSet;
import solution.regression.Trainer;
import solution.regression.Vector;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 6;

        GradesSet trainSet = GradesSet.readSetFromFile(new FileReader("train.csv"), true);
        MoviesSet moviesSet = new MoviesSet(trainSet.getMoviesIds(), n);
        UsersSet usersSet = new UsersSet(trainSet.getUsersIds(), n);

        Trainer trainer = new Trainer(trainSet, moviesSet, usersSet);
        trainer.train();

        GradesSet taskSet = GradesSet.readSetFromFile(new FileReader("task.csv"), false);

        for (Grade grade : taskSet.getGrades()) {
            User user = usersSet.getUserById(grade.getUserId());
            Movie movie = moviesSet.getMovieById(grade.getMovieId());
            grade.setGrade((int) user.getPolygon().calculate(new Vector(movie.getPolygon())));
        }

        taskSet.saveSetToFile("submission.csv");
    }
}
