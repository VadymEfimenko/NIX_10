package ua.com.alevel.persistence.dao.impl;


import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.UserDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.contains(findById(id));
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from User");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        Map<Long, String> map = new HashMap<>();
        Set<Account> accounts = findById(userId).getAccounts();
        for (Account account : accounts) {
            map.put(account.getId(), String.valueOf(account.getType()));
        }
        return map;
    }
}
