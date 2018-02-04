package suadeo;

import java.util.HashMap;

class Recomender {
    private Users users;
    private HashMap<Integer, Double> similarity;

    Recomender(Users users) {
        this.users = users;
        this.similarity = new HashMap<>();
    }

    HashMap<Integer, Double> itemEuclidean(int user) {
        return similarity;
    }

    HashMap<Integer, Double> userEuclidean(int userId) {
        User user = users.getUser(userId);
        calculateSimilarity(user);
        return similarity;
    }

    private void calculateSimilarity(User userA) {
        Euclidean eu = new Euclidean();
        for (User userB : users.getUsers().values()) {
            similarity.put(userB.getId(), eu.calc(userA, userB));
        }
    }
}
