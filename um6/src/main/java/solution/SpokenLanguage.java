package solution;

import org.json.JSONObject;

public class SpokenLanguage {
    private final String englishName;
    private final String iso_639_1;
    private final String name;

    public SpokenLanguage(String englishName, String iso_639_1, String name) {
        this.englishName = englishName;
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getISO_639_1() {
        return iso_639_1;
    }

    public String getName() {
        return name;
    }

    public static SpokenLanguage parseFromJsonObject(JSONObject jsonObject) {
        final String englishName = jsonObject.getString("english_name");
        final String iso_639_1 = jsonObject.getString("iso_639_1");
        final String name = jsonObject.getString("name");
        return new SpokenLanguage(englishName, iso_639_1, name);
    }
}
