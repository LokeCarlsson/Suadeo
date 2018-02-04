package suadeo;

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

    void addUser(int userId, String username) {
        users.putIfAbsent(userId, new User(userId, username));
    }

    Map<Integer, User> getUsers() {
        return users;
    }

    User getUser(int userId) {
        return users.get(userId);
    }
}
