package ua.com.alevel.dto.response;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Transaction;

public class TransactionResponseDto extends ResponseDto {

    private String categoryName;
    private String isIncome;
    private Long sum;
    private String accountName;

    public TransactionResponseDto(Transaction transaction) {
        setId(transaction.getId());
        setCreated(transaction.getCreated());
        this.categoryName = transaction.getCategory();
        this.isIncome = isPositive(transaction.getSum()) ? "income" : "outcome";
        this.sum = transaction.getSum();
        this.accountName = transaction.getAccount().getName();
    }

    private boolean isPositive(Long sum){
        if (sum > 0){
            return true;
        }
        return false;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIsIncome() {
        return isIncome;
    }

    public void setIsIncome(String isIncome) {
        this.isIncome = isIncome;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
