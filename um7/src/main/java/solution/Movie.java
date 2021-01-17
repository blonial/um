package solution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends SimpleMovie {
    private final boolean adult;
    private final String backdropPath;
    private final BelongsToCollection[] belongsToCollection;
    private double budget;
    private final Genre[] genres;
    private final String homepage;
    private final String imdbId;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private double popularity;
    private final String posterPath;
    private final ProductionCompany[] productionCompanies;
    private final ProductionCountry[] productionCountries;
    private final String releaseDate;
    private double revenue;
    private double runtime;
    private final SpokenLanguage[] spokenLanguages;
    private final String status;
    private final String tagline;
    private final String title;
    private final boolean video;
    private double voteAverage;
    private double voteCount;

    public Movie(int movieId,
            int tmbdMovieId,
            boolean adult,
            String backdropPath,
            BelongsToCollection[] belongsToCollection,
            int budget,
            Genre[] genres,
            String homepage,
            String imdbId,
            String originalLanguage,
            String originalTitle,
            String overview,
            double popularity,
            String posterPath,
            ProductionCompany[] productionCompanies,
            ProductionCountry[] productionCountries,
            String releaseDate,
            int revenue,
            int runtime,
            SpokenLanguage[] spokenLanguages,
            String status,
            String tagline,
            String title, boolean video, double voteAverage, double voteCount) {
        super(movieId, tmbdMovieId);
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public boolean isFromGenre(int genreId) {
        for (Genre genre : genres) {
            if (genre.getId() == genreId) {
                return true;
            }
        }
        return false;
    }

    public void normalizeBudget(double min, double max) {
        budget = getNormalizedValue(budget, min, max);
    }

    public void normalizeVoteAverage(double min, double max) {
        voteAverage = getNormalizedValue(voteAverage, min, max);
    }

    public void normalizeVoteCount(double min, double max) {
        voteCount = getNormalizedValue(voteCount, min, max);
    }

    public void normalizePopularity(double min, double max) {
        popularity = getNormalizedValue(popularity, min, max);
    }

    private double getNormalizedValue(double x, double min, double max) {
        return (x - min) / (max - min);
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public BelongsToCollection[] getBelongsToCollection() {
        return belongsToCollection;
    }

    public double getBudget() {
        return budget;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ProductionCompany[] getProductionCompanies() {
        return productionCompanies;
    }

    public ProductionCountry[] getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getRuntime() {
        return runtime;
    }

    public SpokenLanguage[] getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean getVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public static Movie parseFromJson(String json) {
        JSONObject movie = new JSONObject(json);

        final int movieId = movie.getInt("ourId");

        final int tmbdMovieId = movie.getInt("id");

        final boolean adult = movie.getBoolean("adult");

        final String backdropPath = movie.getString("backdrop_path");

        BelongsToCollection[] belongsToCollection;
        try {
            JSONArray belongsToCollectionArray = movie.getJSONArray("belongs_to_collection");
            belongsToCollection = new BelongsToCollection[belongsToCollectionArray.length()];
            for (int i = 0; i < belongsToCollectionArray.length(); i++) {
                belongsToCollection[i] = BelongsToCollection.parseFromJsonObject(belongsToCollectionArray.getJSONObject(
                        i));
            }
        } catch (JSONException e) {
            belongsToCollection = null;
        }

        final int budget = movie.getInt("budget");

        JSONArray genresArray = movie.getJSONArray("genres");
        final Genre[] genres = new Genre[genresArray.length()];
        for (int i = 0; i < genresArray.length(); i++) {
            genres[i] = Genre.parseFromJsonObject(genresArray.getJSONObject(i));
        }

        final String homepage = movie.getString("homepage");

        final String imdbId = movie.getString("imdb_id");

        final String originalLanguage = movie.getString("original_language");

        final String originalTitle = movie.getString("original_title");

        final String overview = movie.getString("overview");

        final double popularity = movie.getDouble("popularity");

        final String posterPath = movie.getString("poster_path");

        JSONArray productionCompaniesArray = movie.getJSONArray("production_companies");
        final ProductionCompany[] productionCompanies = new ProductionCompany[productionCompaniesArray.length()];
        for (int i = 0; i < productionCompaniesArray.length(); i++) {
            productionCompanies[i] = ProductionCompany.parseFromJsonObject(productionCompaniesArray.getJSONObject(i));
        }

        JSONArray productionCountriesArray = movie.getJSONArray("production_countries");
        final ProductionCountry[] productionCountries = new ProductionCountry[productionCountriesArray.length()];
        for (int i = 0; i < productionCountriesArray.length(); i++) {
            productionCountries[i] = ProductionCountry.parseFromJsonObject(productionCountriesArray.getJSONObject(i));
        }

        final String releaseDate = movie.getString("release_date");

        final int revenue = movie.getInt("revenue");

        final int runtime = movie.getInt("runtime");

        JSONArray spokenLanguagesArray = movie.getJSONArray("spoken_languages");
        final SpokenLanguage[] spokenLanguages = new SpokenLanguage[spokenLanguagesArray.length()];
        for (int i = 0; i < spokenLanguagesArray.length(); i++) {
            spokenLanguages[i] = SpokenLanguage.parseFromJsonObject(spokenLanguagesArray.getJSONObject(i));
        }

        final String status = movie.getString("status");

        final String tagline = movie.getString("tagline");

        final String title = movie.getString("title");

        final boolean video = movie.getBoolean("video");

        final double voteAverage = movie.getDouble("vote_average");

        final double voteCount = movie.getDouble("vote_count");

        return new Movie(movieId,
                tmbdMovieId,
                adult,
                backdropPath,
                belongsToCollection,
                budget,
                genres,
                homepage,
                imdbId,
                originalLanguage,
                originalTitle,
                overview,
                popularity,
                posterPath,
                productionCompanies,
                productionCountries,
                releaseDate,
                revenue,
                runtime,
                spokenLanguages,
                status,
                tagline,
                title,
                video,
                voteAverage,
                voteCount);
    }
}
