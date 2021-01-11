package solution;

import org.json.JSONObject;

public class ProductionCountry {
    private final String iso_3166_1;
    private final String name;

    public ProductionCountry(String iso_3166_1, String name) {
        this.iso_3166_1 = iso_3166_1;
        this.name = name;
    }

    public String getISO_3166_1() {
        return iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public static ProductionCountry parseFromJsonObject(JSONObject jsonObject) {
        final String iso_3166_1 = jsonObject.getString("iso_3166_1");
        final String name = jsonObject.getString("name");
        return new ProductionCountry(iso_3166_1, name);
    }
}
