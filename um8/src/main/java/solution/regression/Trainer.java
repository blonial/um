package solution.regression;

import solution.model.grade.Grade;
import solution.model.grade.GradesSet;
import solution.model.movie.Movie;
import solution.model.movie.MoviesSet;
import solution.model.user.User;
import solution.model.user.UsersSet;

public class Trainer {
    private final GradesSet gradesSet;
    private final MoviesSet moviesSet;
    private final UsersSet usersSet;

    public Trainer(GradesSet gradesSet, MoviesSet moviesSet, UsersSet usersSet) {
        this.gradesSet = gradesSet;
        this.moviesSet = moviesSet;
        this.usersSet = usersSet;
    }

    public void train() {
        final double learningRate = 0.0001;
        final double breakStepSize = 0.001;
        final double maxIterations = 100000;

        int realIterations = 0;

        while (realIterations < maxIterations) {
            for (Grade grade : gradesSet.getGrades()) {
                Movie movie = moviesSet.getMovieById(grade.getMovieId());
                User user = usersSet.getUserById(grade.getUserId());

                movie.addToNewFactorsToAdd(calculateNewFactors(movie.getPolygon(),
                        new Vector(user.getPolygon()),
                        grade.getGrade()));
                user.addToNewFactorsToAdd(calculateNewFactors(user.getPolygon(),
                        new Vector(movie.getPolygon()),
                        grade.getGrade()));
            }

            double moviesSetLoss = moviesSet.applyNewFactors(learningRate);
            double usersSetLost = usersSet.applyNewFactors(learningRate);

            double loss = Math.max(moviesSetLoss, usersSetLost);

            realIterations++;
            if (loss < breakStepSize) {
                break;
            }
        }
    }

    private double[] calculateNewFactors(Polygon polygon, Vector vector, int grade) {
        LossFunction lossFunction = new LossFunction(polygon);
        double[] newFactors = new double[polygon.getPolygonParams().length];
        for (int i = 0; i < polygon.getPolygonParams().length; i++) {
            newFactors[i] = lossFunction.calculateLoss(polygon.getPolygonParams()[i], vector, grade);
        }
        return newFactors;
    }
}
