package solution;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductionCompany {
    private final int id;
    private final String logoPath;
    private final String name;
    private final String originCountry;

    public ProductionCompany(int id, String logoPath, String name, String originCountry) {
        this.id = id;
        this.logoPath = logoPath;
        this.name = name;
        this.originCountry = originCountry;
    }

    public int getId() {
        return id;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public static ProductionCompany parseFromJsonObject(JSONObject jsonObject) {
        final int id = jsonObject.getInt("id");

        String logoPath;
        try{
            logoPath = jsonObject.getString("logo_path");
        } catch (JSONException e) {
            logoPath = null;
        }

        final String name = jsonObject.getString("name");

        final String originCountry = jsonObject.getString("origin_country");

        return new ProductionCompany(id, logoPath, name, originCountry);
    }
}
