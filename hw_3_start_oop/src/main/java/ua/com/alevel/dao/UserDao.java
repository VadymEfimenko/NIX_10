package ua.com.alevel.dao;

import ua.com.alevel.db.DBUser;
import ua.com.alevel.entity.User;

public class UserDao {
    public void create(User user) {
        DBUser.getInstance().create(user);
    }

    public void update(User user) {
        DBUser.getInstance().update(user);
    }

    public void delete(String id) {
        DBUser.getInstance().delete(id);
    }

    public User findById(String id) {
        return DBUser.getInstance().findById(id);
    }

    public User[] findAll() {
        return DBUser.getInstance().findAll();
    }
}
