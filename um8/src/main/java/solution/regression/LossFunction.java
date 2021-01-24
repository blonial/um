package solution.regression;

public class LossFunction {
    private final Polygon polygon;

    public LossFunction(Polygon polygon) {
        this.polygon = polygon;
    }

    public double calculateLoss(PolygonFragment fragment, Vector vector, int expectedGrade) {
        return (this.polygon.calculate(vector) - expectedGrade) * fragment.getX(vector);
    }
}
