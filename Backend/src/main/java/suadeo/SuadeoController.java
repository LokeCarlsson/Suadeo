package suadeo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuadeoController {

    private Users users = new Users();
    private Movies movies = new Movies();
    private Recomender recomender;

    @Autowired
    void start() throws IOException {
        Generator generator = new Generator(movies, users);
        if (users.size() <= 0) {
            generator.generateMovies();
            generator.generateUsers();
        }

        recomender = new Recomender(movies, users);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/users")
    public String users() throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("{ \"payload\": [");
        for (User user : users.getUsers().values()) {
            sb.append("{\"username\": ").append("\"").append(user.getName()).append("\",");
            sb.append("\"id\": ").append("\"").append(user.getId()).append("\"");
            sb.append("}");
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("]}");
        return sb.toString();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/recommendations")
    public String usersRatings() throws IOException {
        return "hej";
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping("/ratings")
    public String ratings(@RequestParam(value="user", defaultValue="") int userId) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append("{ \"payload\": [");
        for (Integer movieId : users.getUser(userId).getRatings().keySet()) {
            sb.append("{\"id\": ").append(movieId).append(", ");
            sb.append("\"name\": ").append("\"").append(movies.getMovie(movieId).getName().replace("\"", "")).append("\", ");
            sb.append("\"rating\": ").append(users.getUser(userId).getRating(movieId));
            sb.append(" }, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("]}");

        return sb.toString();
    }

    @RequestMapping("/euclidean/user")
    public String userEuclidean(@RequestParam(value="user", defaultValue="") int user) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"payload\": [");
        for (Map.Entry<Integer, Double> score : recomender.userEuclidean(user).entrySet()) {
            sb.append("{\"id\": ").append(score.getKey()).append(", ");
            sb.append("\"rating\": ").append(score.getValue());
            sb.append(" }, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("]}");
        return sb.toString();
    }

    @RequestMapping("/euclidean/item")
    public String itemEuclidean(@RequestParam(value="user", defaultValue="") int user) throws IOException, URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"payload\": [");
        for (Map.Entry<String, Double> score : recomender.itemEuclidean(user).entrySet()) {
            sb.append("{\"movie\": \"").append(score.getKey().replace("\"","")).append("\", ");
            sb.append("\"rating\": ").append(score.getValue());
            sb.append(" }, ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("]}");
        return sb.toString();
    }
}
