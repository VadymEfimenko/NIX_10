package ua.com.alevel.db;

import org.apache.commons.lang3.ArrayUtils;
import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public class DBUser {

    private static User[] users;
    private static DBUser instance;

    private DBUser() {
        users = new User[0];
    }

    public static DBUser getInstance() {
        if (instance == null) {
            instance = new DBUser();
        }
        return instance;
    }

    public static User[] findAll() {
        return users;
    }

    public void create(User user) {
        user.setId(generateId());
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user != null && user.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public User findById(String id) {
        for (User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        throw new RuntimeException("Пользователя не существует");
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setAge(user.getAge());
        current.setEmail(user.getEmail());
    }

    public void delete(String id) {
        for (int i = 0; i < users.length; i++) {
            if (id.equals(users[i].getId())) {
                users = ArrayUtils.removeElement(users, users[i]);
            }
        }
    }
}

