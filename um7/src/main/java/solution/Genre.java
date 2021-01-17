package solution;

import org.json.JSONObject;

public class Genre {
    private final int id;
    private final String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Genre parseFromJsonObject(JSONObject jsonObject) {
        final int id = jsonObject.getInt("id");
        final String name = jsonObject.getString("name");
        return new Genre(id, name);
    }
}
