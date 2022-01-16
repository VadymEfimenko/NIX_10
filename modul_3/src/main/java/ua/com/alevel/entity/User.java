package ua.com.alevel.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "user_id"))

    private String name;

    @OneToMany(mappedBy = "user", cascade = {
            CascadeType.REMOVE
    })
    private Set<Account> accounts;

    public User() {
        super();
        this.accounts = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
