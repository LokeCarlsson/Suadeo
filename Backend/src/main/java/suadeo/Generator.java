package suadeo;

import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;

class Generator {
    private Movies movies;
    private Users users;

    Generator(Movies movies, Users users) {
        this.movies = movies;
        this.users = users;
    }

    void generateMovies() throws IOException {
        System.out.println("Generating movies!!");
        Files
                .lines(ResourceUtils.getFile("classpath:data/movies_x.csv").toPath()).skip(1).forEach(payload -> {
            String[] data =  payload.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            movies.addMovie(Integer.parseInt(data[0]), data[1]);
        });
    }

    void generateUsers() throws IOException {
        System.out.println("Generating users!!");
        Files
                .lines(ResourceUtils.getFile("classpath:data/ratings_x.csv").toPath()).skip(1).forEach(payload -> {
            String[] data = payload.split(",");
            users.addUser(Integer.parseInt(data[0]), "User " + data[0]);
            User user = users.getUser(Integer.parseInt(data[0]));
            Movie movie = movies.getMovie(Integer.parseInt(data[1]));
            user.addRating(movie.getId(), Double.parseDouble(data[2]));
            movie.addRating(user.getId(), Double.parseDouble(data[2]));
        });
    }
}