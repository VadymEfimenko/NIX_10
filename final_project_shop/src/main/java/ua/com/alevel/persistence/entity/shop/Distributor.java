package ua.com.alevel.persistence.entity.shop;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "distributors")
public class Distributor extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "distributor_id"))

    private String name;
    private String website;
    private String digitalMedia;
    private String format;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "distributor_release",
            joinColumns = @JoinColumn(name = "distributor_id"),
            inverseJoinColumns = @JoinColumn(name = "release_id"))
    private Set<Release> releases;

    @OneToMany(mappedBy = "distributor")
    private Set<Order> orders;

    public Distributor() {
        super();
        this.orders = new HashSet<>();
        this.releases= new HashSet<>();
    }

    public void addRelease(Release release) {
        releases.add(release);
        release.getDistributors().add(this);
    }

    public void removeRelease(Release release) {
        releases.remove(release);
        release.getDistributors().remove(this);
    }
}
