package solution;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieSet {
    private final Movie[] movieSet;
    private final Integer[] genresIds;
    private final String[] originalLanguages;

    public MovieSet(Movie[] movieSet) {
        this.movieSet = movieSet;

        // genres ids
        List<Integer> genresIds = new ArrayList<>();
        for (Movie movie : movieSet) {
            for (Genre genre : movie.getGenres()) {
                if (!genresIds.contains(genre.getId())) {
                    genresIds.add(genre.getId());
                }
            }
        }
        this.genresIds = genresIds.toArray(Integer[]::new);

        // originalLanguages
        List<String> originalLanguages = new ArrayList<>();
        for (Movie movie : movieSet) {
            if (!originalLanguages.contains(movie.getOriginalLanguage())) {
                originalLanguages.add(movie.getOriginalLanguage());
            }
        }
        this.originalLanguages = originalLanguages.toArray(String[]::new);

        normalizeValues();
    }

    private void normalizeValues() {
        // normalize budget
        double min = movieSet[0].getBudget();
        double max = movieSet[0].getBudget();
        for(Movie movie : movieSet) {
            min = Math.min(min, movie.getBudget());
            max = Math.max(max, movie.getBudget());
        }
        for(Movie movie : movieSet) {
            movie.normalizeBudget(min, max);
        }

        // normalize vote count
        min = movieSet[0].getVoteCount();
        max = movieSet[0].getVoteCount();
        for(Movie movie : movieSet) {
            min = Math.min(min, movie.getVoteCount());
            max = Math.max(max, movie.getVoteCount());
        }
        for(Movie movie : movieSet) {
            movie.normalizeVoteCount(min, max);
        }

        // normalize vote average
        min = movieSet[0].getVoteAverage();
        max = movieSet[0].getVoteAverage();
        for(Movie movie : movieSet) {
            min = Math.min(min, movie.getVoteAverage());
            max = Math.max(max, movie.getVoteAverage());
        }
        for(Movie movie : movieSet) {
            movie.normalizeVoteAverage(min, max);
        }

        // normalize popularity
        min = movieSet[0].getPopularity();
        max = movieSet[0].getPopularity();
        for(Movie movie : movieSet) {
            min = Math.min(min, movie.getPopularity());
            max = Math.max(max, movie.getPopularity());
        }
        for(Movie movie : movieSet) {
            movie.normalizePopularity(min, max);
        }
    }

    public Movie[] getMovieSet() {
        return movieSet;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movieSet) {
            if (movie.getMovieId() == id) {
                return movie;
            }
        }
        return null;
    }

    public Integer[] getGenresIds() {
        return genresIds;
    }

    public String[] getOriginalLanguages() {
        return originalLanguages;
    }

    public static MovieSet readSetFromFile(Reader reader) {
        BufferedReader input = new BufferedReader(reader);
        Movie[] movies = input.lines().map(Movie::parseFromJson).toArray(Movie[]::new);
        return new MovieSet(movies);
    }
}
