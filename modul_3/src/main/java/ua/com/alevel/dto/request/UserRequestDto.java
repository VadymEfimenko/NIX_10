package ua.com.alevel.dto.request;

import ua.com.alevel.dto.RequestDto;

import java.util.Set;

public class UserRequestDto extends RequestDto {

    private String name;
    private Set<Long> accountsId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Set<Long> accountsId) {
        this.accountsId = accountsId;
    }
}
