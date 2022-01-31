package ua.com.alevel.persistence.entity.release;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.listener.ReleaseVisibleGenerationListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "releases")
public class Release extends BaseEntity {

    @AttributeOverride(name = "id", column = @Column(name = "release_id"))

    @Column(name = "release_name")
    private String releaseName;

    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer year;

    private String genre;

    private Integer quantity;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE
    })
    @JoinTable(
            name = "release_order",
            joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @ManyToMany(mappedBy = "releases", cascade = {
            CascadeType.PERSIST
    })
    private Set<Label> labels;

    @ManyToOne
    private Musician musician;

    public Release() {
        super();
        labels = new HashSet<>();
        orders = new HashSet<>();
    }

    public Set<Label> getLabels() {
        return labels;
    }

    @PreRemove
    private void removeRelease() {
        musician.getReleases().remove(this);
        this.setMusician(null);

    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Musician getMusician() {
        return musician;
    }

    public void setMusician(Musician musician) {
        this.musician = musician;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Release{" +
                "releaseName='" + releaseName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", quantity=" + quantity +
                ", labels=" + labels +
                ", musician=" + musician +
                '}';
    }
}
