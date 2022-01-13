package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "category_id"))

    @Enumerated(EnumType.STRING)
    private CascadeType categoryType;

    @Column(name = "is_income")
    private Boolean isIncome;

    @OneToMany(mappedBy = "category")
    private Set<Transaction> transactions;

    public Category() {
        super();
        this.transactions = new HashSet<>();
    }

    public CascadeType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CascadeType categoryType) {
        this.categoryType = categoryType;
    }

    public Boolean getIncome() {
        return isIncome;
    }

    public void setIncome(Boolean income) {
        isIncome = income;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
