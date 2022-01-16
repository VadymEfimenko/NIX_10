package ua.com.alevel.dto.request;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.entity.Account;

public class TransactionRequestDto extends RequestDto {

    private Account account;
    private String category;
    private Long sum;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
