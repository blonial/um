package solution.model.user;

public class UsersSet {
    private final User[] usersSet;

    public UsersSet(Integer[] userIds, int n) {
        usersSet = new User[userIds.length];
        for (int i = 0; i < usersSet.length; i++) {
            usersSet[i] = new User(userIds[i], n);
        }
    }

    public User[] getUsersSet() {
        return usersSet;
    }

    public User getUserById(int id) {
        for (User user : usersSet) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public double applyNewFactors(double learningRate) {
        double loss = 0.0;
        for (User user : usersSet) {
            loss = Math.max(user.applyNewFactors(learningRate), loss);
        }
        return loss;
    }
}
