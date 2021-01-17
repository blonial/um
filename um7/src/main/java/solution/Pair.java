package solution;

public class Pair {
    private final Grade grade;
    private final double distance;

    public Pair(Grade grade, double distance) {
        this.grade = grade;
        this.distance = distance;
    }

    public Grade getGrade() {
        return grade;
    }

    public double getDistance() {
        return distance;
    }
}
