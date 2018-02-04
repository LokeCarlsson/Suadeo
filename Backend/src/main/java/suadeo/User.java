package suadeo;

import java.util.HashMap;
import java.util.Map;

class User {
    private int id;
    private String name;
    HashMap<Integer, Double> ratings;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.ratings = new HashMap<>();
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void addRating(int movieId, double rating) {
        ratings.put(movieId, rating);
    }

    Double getRating(Integer id) {
        return ratings.get(id);
    }

    Map<Integer, Double> getRatings() {
        return ratings;
    }
}