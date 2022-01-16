package ua.com.alevel.service;

import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;

public interface AccountService extends BaseService<Account>{

    User findUserByAccountId(Long id);
}
