package ua.com.alevel.service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

public class UserService {

    private static final UserDao userDao = new UserDao();

    public static User[] findAll() {
        return userDao.findAll();
    }

    public void create(User user) {
        userDao.create(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(String id) {
        userDao.delete(id);
    }

    public User findById(String id) {
        return userDao.findById(id);
    }
}
