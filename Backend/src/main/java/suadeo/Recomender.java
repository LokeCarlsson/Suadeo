package suadeo;

import java.util.HashMap;

class Recomender {
    private Users users;
    private Movies movies;
    private HashMap<Integer, Double> similarity;
    private HashMap<Integer, Double> similaritySum;
    private HashMap<Integer, Double> weightedScores;

    Recomender(Movies movies, Users users) {
        this.users = users;
        this.movies = movies;
        this.similarity = new HashMap<>();
        this.similaritySum = new HashMap<>();
        this.weightedScores = new HashMap<>();
    }

    HashMap<String, Double> itemEuclidean(int userId) {
        HashMap<String, Double> result = new HashMap<>();
        User user = users.getUser(userId);

        calculateSimilarity(user);
        calculateWeight(user);
        calculateSimSum(user);

        similaritySum.forEach((movieId, similarity) -> {
            double total = (weightedScores.get(movieId) / similarity);
            if (similarity > 0) result.put(movies.getMovies().get(movieId).getName(), total);
        });

        return result;
    }

    HashMap<Integer, Double> userEuclidean(int userId) {
        User user = users.getUser(userId);
        calculateSimilarity(user);
        return similarity;
    }

    private void calculateSimilarity(User userA) {
        Euclidean euclidean = new Euclidean();
        for (User userB : users.getUsers().values()) {
            similarity.put(userB.getId(), euclidean.calc(userA, userB));
        }
    }

    private void calculateWeight(User userA) {
        movies.getMovies().forEach((id, movie) -> {
            if (!weightedScores.containsKey(id)) {
                weightedScores.put(id, 0.0);
            }
        });

        similarity.forEach((id, sim) -> {
            if (userA.getId() != id) {
                users.getUser(id).getRatings().forEach((movieId, rating) -> {
                    if (weightedScores.containsKey(movieId)) {
                        weightedScores.replace(movieId, sim * rating + weightedScores.get(movieId));
                    } else {
                        weightedScores.put(movieId, sim * rating);
                    }
                });
            }
        });
    }

    private void calculateSimSum(User userA) {
        users.getUsers().forEach((id, user) -> {
            user.getRatings().forEach((movieId, rating) -> {
                if (userA.getId() != id) {
                    double sim = similarity.get(id);
                    if (similaritySum.containsKey(movieId)) {
                        similaritySum.replace(movieId, similaritySum.get(movieId) + sim);
                    } else {
                        similaritySum.put(movieId, sim);
                    }
                }
            });
        });
    }
}
