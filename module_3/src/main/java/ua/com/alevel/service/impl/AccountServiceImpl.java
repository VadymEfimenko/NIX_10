package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public User findUserByAccountId(Long accountId) {
        return null;
    }

    @Override
    public void create(Account entity) {

    }

    @Override
    public void update(Account entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        return null;
    }
}
