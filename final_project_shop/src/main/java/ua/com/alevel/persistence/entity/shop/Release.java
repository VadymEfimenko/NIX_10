package ua.com.alevel.persistence.entity.shop;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.enums.ReleaseType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "releases")
public class Release extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "release_id"))

    private String name;
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;
    private Integer quantity;
    private Boolean visible;

    @Enumerated(EnumType.STRING)
    @Column(name = "release_types", nullable = false)
    private ReleaseType releaseType;

    @ManyToMany(mappedBy = "releases", cascade = {
            CascadeType.PERSIST
    })
    private Set<Distributor> distributors;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "release_order",
            joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    @ManyToOne()
    private Musician musician;

    public Release() {
        super();
        this.orders = new HashSet<>();
        this.distributors = new HashSet<>();
    }

    @PreRemove
    private void removeRelease() {
        musician.getReleases().remove(this);
        this.setMusician(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Release)) return false;

        Release release = (Release) o;

        return new EqualsBuilder().append(getName(), release.getName()).append(getPrice(), release.getPrice()).append(getDescription(), release.getDescription()).append(getImage(), release.getImage()).append(getQuantity(), release.getQuantity()).append(getVisible(), release.getVisible()).append(getReleaseType(), release.getReleaseType()).append(getDistributors(), release.getDistributors()).append(getOrders(), release.getOrders()).append(getMusician(), release.getMusician()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getName()).append(getPrice()).append(getDescription()).append(getImage()).append(getQuantity()).append(getVisible()).append(getReleaseType()).append(getDistributors()).append(getOrders()).append(getMusician()).toHashCode();
    }
}
