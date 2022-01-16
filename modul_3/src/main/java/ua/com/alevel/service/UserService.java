package ua.com.alevel.service;

import ua.com.alevel.entity.User;

import java.util.Map;

public interface UserService extends BaseService <User> {

    Map<Long, String> findAllAccountsByUserId(Long userId);
}
