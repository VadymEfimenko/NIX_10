package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        return null;
    }
}
