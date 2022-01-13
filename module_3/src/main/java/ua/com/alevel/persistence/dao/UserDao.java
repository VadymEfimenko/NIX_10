package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.User;

import java.util.Map;

public interface UserDao extends BaseDao<User> {

    Map<Long, String> findAllAccountsByUserId(Long userId);
}
