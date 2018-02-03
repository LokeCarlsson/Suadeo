package suadeo;

import java.util.HashMap;
import java.util.Map;

class Movie {
    private int id;
    private String name;
    private Map<Integer, Double> ratings = new HashMap<>();

    Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }


    void addRating(int userId, double rating) {
        ratings.put(userId, rating);
    }

    Map<Integer, Double> getRatings() {
        return ratings;
    }
}
