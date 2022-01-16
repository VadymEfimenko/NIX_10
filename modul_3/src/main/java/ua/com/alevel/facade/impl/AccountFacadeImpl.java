package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.AccountRequestDto;
import ua.com.alevel.dto.response.AccountResponseDto;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final UserService userService;

    public AccountFacadeImpl(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public User findUserByAccountId(Long accountId) {
        return accountService.findUserByAccountId(accountId);
    }

    @Override
    public void create(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setBalance(1_000_000L);
        account.setUser(userService.findById(accountRequestDto.getUserId()));
        account.setName(accountRequestDto.getName());
        account.setCreated(new Timestamp(System.currentTimeMillis()));
        accountService.create(account);
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return new AccountResponseDto(accountService.findById(id));
    }

    @Override
    public List<AccountResponseDto> findAll() {
        return null;
    }
}
