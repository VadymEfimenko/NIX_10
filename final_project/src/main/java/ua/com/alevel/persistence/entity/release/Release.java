package ua.com.alevel.persistence.entity.release;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.musician.Musician;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "releases")
public class Release extends BaseEntity {

    @Column(name = "release_name")
    private String releaseName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private Integer year;

    private String genre;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Label label;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "releases")
    private Set<Musician> musicians;

    public Release(){
        super();
        musicians = new HashSet<>();
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Set<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(Set<Musician> musicians) {
        this.musicians = musicians;
    }
}
