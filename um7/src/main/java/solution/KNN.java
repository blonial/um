package solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KNN {
    private final GradeSet trainSet;
    private final MovieSet movieSet;

    public KNN(GradeSet trainSet, MovieSet movieSet) {
        this.trainSet = trainSet;
        this.movieSet = movieSet;
    }

    public int predictValue(Grade searchedGrade, int k) {
        GradeSet searchedMovieGrades = trainSet.getGradesByMovieId(searchedGrade.getMovieId());
        GradeSet searchedUserGrades = trainSet.getGradesByUserId(searchedGrade.getUserId());

        List<Pair> distances = new ArrayList<>();
        for (Grade otherUser : searchedMovieGrades.getGrades()) {
            GradeSet otherUserGrades = trainSet.getGradesByUserId(otherUser.getUserId());
            distances.add(new Pair(otherUser,
                    searchedGrade.calculateUserSimilarity(searchedUserGrades, otherUserGrades)));
        }
        return calculateResult(distances, k);
    }

    private int calculateResult(List<Pair> distances, int k) {
        distances.sort(Comparator.comparingDouble(Pair::getDistance));
        int[] counts = new int[6];
        for (int i = 0; i < k; i++) {
            counts[distances.get(i).getGrade().getGrade()]++;
        }
        int result = 0;
        int count = counts[0];
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > count) {
                result = i;
                count = counts[i];
            }
        }
        return result;
    }
}
