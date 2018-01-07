package suadeo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Users {
    private Map<Integer, User> users;

    Users() {
        users = new HashMap<>();
    }

    Integer size() {
        return users.size();
    }

    void generate() throws IOException {
        ArrayList<String> lines = (ArrayList<String>) Files
                .readAllLines(Paths.get("./resources/data/ratings.csv"), Charset.defaultCharset());
        lines
                .forEach(line -> Arrays
                        .stream(line.split(","))
                        .forEach(System.out::println));
    }

    User addUser(int userId, String username) {
        return users.put(userId, new User(userId, username));
    }

    Map<Integer, User> getUsers() {
        return users;
    }

    User getUser(int userId) {
        return users.get(userId);
    }
}
