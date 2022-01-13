package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

public interface AccountService extends BaseService<Account> {

    User findUserByAccountId(Long accountId);
}
