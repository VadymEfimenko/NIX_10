package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Account entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public User findUserByAccountId(Long id) {
        return findById(id).getUser();
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }
}
