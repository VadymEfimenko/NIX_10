package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.enums.OrderStatus;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @ManyToOne
    private Distributor distributor;

    @ManyToMany(mappedBy = "orders", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Release> releases;

    @ManyToOne
    private User user;

    public Order() {
        super();
        this.releases = new HashSet<>();
    }

    @PreRemove
    private void removeOrder() {
        this.getReleases().stream().forEach(release -> {
            release.setQuantity(release.getQuantity() + 1);
            release.getOrders().remove(this);
            ReleaseVisibleGenerationListener.generateReleaseVisible(release);
        });
        this.setReleases(Collections.emptySet());
    }
}
