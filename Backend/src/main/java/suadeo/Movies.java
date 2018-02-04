package suadeo;

import java.util.HashMap;
import java.util.Map;

class Movies {
    private Map<Integer, Movie> movies;

    Movies() {
        movies = new HashMap<>();
    }

    void addMovie(int id, String name) {
        Movie newMovie = new Movie(id, name);
        movies.put(id, newMovie);
    }

    Movie getMovie(int id) {
        return movies.get(id);
    }

    Map<Integer, Movie> getMovies() {
       return movies;
    }
}
