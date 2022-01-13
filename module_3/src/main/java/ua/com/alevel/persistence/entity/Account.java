package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.accountType.AccountType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "account_id"))

    private Long balance;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
        super();
        this.transactions = new HashSet<>();
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
