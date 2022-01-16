package ua.com.alevel.dao;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;

public interface AccountDao extends BaseDao<Account> {
    User findUserByAccountId(Long id);
    void update(Account account);
}
