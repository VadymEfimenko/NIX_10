package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public class DBUser {

    private static User[] users;
    private static DBUser instance;

    private DBUser() {
        users = new User[0];
    }

    private static DBUser getInstance() {
        if (instance == null) {
            instance = new DBUser();
        }
        return instance;
    }

    public static User[] findAll() {
        return users;
    }

    public static int[] removeTheElement(Object[] arr, int index) {
        int[] anotherArray = new int[arr.length - 1];
        System.arraycopy(arr, 0, anotherArray, 0, index);
        System.arraycopy(arr, index + 1,
                anotherArray, index,
                arr.length - index - 1);
        return anotherArray;
    }

    public void create(User user) {
        user.setId(generateId());
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
    }

    public String generateId() {
        String id = UUID.randomUUID().toString();
        for (User user : users) {
            if (user.getId().equals(id)) {
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
        User userDelete = findById(id);
        Arrays.sort(users);
        int position = Arrays.binarySearch(users, userDelete);
        removeTheElement(users, position);
    }
}
