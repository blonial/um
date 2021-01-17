package solution;

import java.util.ArrayList;
import java.util.List;

public class FeatureVector {

    private final Double values[];
    private final int result;

    public FeatureVector(Grade grade, MovieSet movieSet) {
        Integer[] genresIds = movieSet.getGenresIds();
        Movie movie = movieSet.getMovieById(grade.getMovieId());

        List<Double> vectorValues = new ArrayList<>();

        // cecha - czy jest w kategorii
        for (Integer genreId : genresIds) {
            vectorValues.add(movie.isFromGenre(genreId) ? 1.0 : 0);
        }
        // cecha - znormalizowany budżet
        vectorValues.add(movie.getBudget());
        // cecha - znormalizowana średnia ocena
        vectorValues.add(movie.getVoteAverage());
        // cecha - znormalizowana ilość ocen
        vectorValues.add(movie.getVoteCount());
        // cecha - znormalizowana popularność
        vectorValues.add(movie.getPopularity());

        values = vectorValues.toArray(Double[]::new);
        result = grade.getGrade();
    }

    public double calculateDistance(FeatureVector featureVector) {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += (values[i] - featureVector.values[i]) * (values[i] - featureVector.values[i]);
        }
        return Math.sqrt(sum);
    }

    public int getResult() {
        return result;
    }
}
