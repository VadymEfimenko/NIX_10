package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class UserDaoImpl implements UserDao {

    private final JpaConfig jpaConfig;

    @PersistenceContext
    EntityManager entityManager;

    public UserDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(rootEntry);
        List<User> allQuery = entityManager.createQuery(all).getResultList();
        return allQuery;
    }

    private User convertResultSetToUser(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        String name = resultSet.getString("name");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCreated(new Date(created.getTime()));
        return user;
    }

    @Override
    public Map<Long, String> findAllAccountsByUserId(Long userId) {
        Map<Long, String> map = new HashMap<>();
        Set<Account> accounts = findById(userId).getAccounts();
        for (Account account : accounts) {
            map.put(account.getId(), account.getName());
        }
        return map;
    }
}
