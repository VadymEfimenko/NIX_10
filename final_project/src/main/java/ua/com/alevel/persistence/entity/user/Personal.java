package ua.com.alevel.persistence.entity.user;


import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
//        AgeByBirthDayGenerationListener.class
})
public class Personal extends User {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    public Personal() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
