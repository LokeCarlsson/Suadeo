package suadeo;

import java.util.HashMap;

class User {
    private int id;
    private String name;
    HashMap<Integer, Double> ratings;

    User(int id, String name) {
        this.id = id;
        this.name = name;
        this.ratings = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}