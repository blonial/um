package solution.model.user;

import solution.model.RegressionObject;
import solution.regression.Polygon;

public class User extends RegressionObject {
    public User(int id, int n) {
        super(id);
        polygon = new Polygon(n + 1, 1);
    }
}
