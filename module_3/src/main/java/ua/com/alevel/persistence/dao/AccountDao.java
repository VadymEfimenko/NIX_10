package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

public interface AccountDao extends BaseDao<Account>{
    User findUserByAccountId(Long accountId);
}
