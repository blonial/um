package solution.model.movie;

import solution.model.RegressionObject;
import solution.regression.Polygon;

public class Movie extends RegressionObject {
    public Movie(int id, int n) {
        super(id);
        polygon = new Polygon(n + 1, 1, true);
    }
}
