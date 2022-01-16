package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public User findUserByAccountId(Long id) {
        return accountDao.findUserByAccountId(id);
    }

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return null;
    }
}
