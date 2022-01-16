package ua.com.alevel.dto.response;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Account;

public class AccountResponseDto extends ResponseDto {

    private Long balance;
    private String name;
    private Long Id;

    public AccountResponseDto() {
    }

    public AccountResponseDto(Account account){
        setId(account.getId());
        setCreated(account.getCreated());
        this.balance = (account.getBalance());
        this.name = (account.getName());
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
    }
}
