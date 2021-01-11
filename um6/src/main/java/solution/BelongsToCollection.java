package solution;

import org.json.JSONObject;

public class BelongsToCollection {
    private final int id;
    private final String name;
    private final String posterPath;
    private final String backdropPath;

    public BelongsToCollection(int id, String name, String posterPath, String backdropPath) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public static BelongsToCollection parseFromJsonObject(JSONObject jsonObject) {
        final int id = jsonObject.getInt("id");
        final String name = jsonObject.getString("name");
        final String posterPath = jsonObject.getString("poster_path");
        final String backdropPath = jsonObject.getString("backdrop_path");
        return new BelongsToCollection(id, name, posterPath, backdropPath);
    }
}
