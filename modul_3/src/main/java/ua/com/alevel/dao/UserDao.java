package ua.com.alevel.dao;

import ua.com.alevel.entity.User;

import java.util.Map;

public interface UserDao extends BaseDao<User>{
    Map<Long, String> findAllAccountsByUserId(Long userId);
}
