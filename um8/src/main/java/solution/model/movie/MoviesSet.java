package solution.model.movie;

public class MoviesSet {
    private final Movie[] moviesSet;

    public MoviesSet(Integer[] moviesIds, int n) {
        moviesSet = new Movie[moviesIds.length];
        for (int i = 0; i < moviesIds.length; i++) {
            moviesSet[i] = new Movie(moviesIds[i], n);
        }
    }

    public Movie[] getMoviesSet() {
        return moviesSet;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : moviesSet) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public double applyNewFactors(double learningRate) {
        double loss = 0.0;
        for (Movie movie : moviesSet) {
            loss = Math.max(movie.applyNewFactors(learningRate), loss);
        }
        return loss;
    }
}
