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
        this.grade = -1;
    }

    public Grade(int id, int userId, int movieId, int grade) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.grade = grade;
    }

    public double calculateUserSimilarity(GradeSet myGrades, GradeSet otherUserGrades) {
        double differencesBetweenSameMovieGrades = 0;
        double sameMoviesRatedCount = 0;
        for (Grade myGrade : myGrades.getGrades()) {
            for (Grade otherUserGrade : otherUserGrades.getGrades()) {
                if (myGrade.getMovieId() == otherUserGrade.getMovieId()) {
                    differencesBetweenSameMovieGrades += Math.abs(myGrade.getGrade() - otherUserGrade.getGrade());
                    sameMoviesRatedCount++;
                    break;
                }
            }
        }
        return sameMoviesRatedCount != 0 ? differencesBetweenSameMovieGrades - 0.01 * sameMoviesRatedCount: Double.MAX_VALUE;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setRandomGrade() {
        this.grade = (int) (Math.random() * 6) % 6;
    }

    public String getCSVLine() {
        return this.id + ";" + this.userId + ";" + this.movieId + ";" + this.grade;
    }
}
