package solution;

public class Grade {
    private int id;
    private int userId;
    private int movieId;
    private int grade;

    public Grade(int id, int userId, int movieId) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
    }

    public void setRandomGrade() {
        this.grade = (int) (Math.random() * 6) % 6;
    }

    public String getCSVLine() {
        return this.id + ";" + this.userId + ";" + this.movieId + ";" + this.grade;
    }
}
