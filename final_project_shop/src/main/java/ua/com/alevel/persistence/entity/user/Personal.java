package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.enums.RoleType;
import ua.com.alevel.persistence.listener.FullNameGenerationListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@DiscriminatorValue("PERSONAL")
@EntityListeners({
      FullNameGenerationListener.class,
})
public class Personal extends User{

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
}
