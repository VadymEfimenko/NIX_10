package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;
import ua.com.alevel.persistence.type.OrderStatus;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private Label label;

    @ManyToMany(mappedBy = "orders",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<Release> releases;

    @ManyToOne
    private User user;

    public Order(){
        super();
        this.releases = new HashSet<>();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PreRemove
    private void removeOrder(){
        this.getReleases().stream().forEach(release -> {release.setQuantity(release.getQuantity()+1);
            release.getOrders().remove(this);
            ReleaseVisibleGenerationListener.generateReleaseVisible(release);
        });
        this.setReleases(Collections.emptySet());
    }
}
