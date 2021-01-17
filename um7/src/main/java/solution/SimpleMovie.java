package solution;

public class SimpleMovie {
    private final int movieId;
    private final int tmbdMovieId;

    public SimpleMovie(int movieId, int tmbdMovieId) {
        this.movieId = movieId;
        this.tmbdMovieId = tmbdMovieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getTmbdMovieId() {
        return tmbdMovieId;
    }
}
