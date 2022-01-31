package ua.com.alevel.persistence.entity.label;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.entity.user.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "label_id"))

    @Column(name = "label_name")
    private String labelName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "label_release",
            joinColumns = @JoinColumn(name = "label_id"),
            inverseJoinColumns = @JoinColumn(name = "release_id"))
    private Set<Release> releases;

    @OneToMany(mappedBy = "label")
    private Set<Order> orders;

    public Label(){
        super();
        this.releases = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<Release> getReleases() {
        return releases;
    }

    public void setReleases(Set<Release> releases) {
        this.releases = releases;
    }

    public void removeRelease(Release release) {
        release.getLabels().remove(this);
        releases.remove(release);
    }

    public void addRelease(Release release) {
        releases.add(release);
        release.getLabels().add(this);
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelName='" + labelName + '\'' +
                ", releases=" + releases +
                ", orders=" + orders +
                '}';
    }
}
