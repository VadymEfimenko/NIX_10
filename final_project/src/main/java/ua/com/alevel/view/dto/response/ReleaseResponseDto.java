package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.release.Release;

import java.util.Set;

public class ReleaseResponseDto extends ResponseDto {

    private String releaseName;
    private String description;
    private String imageUrl;
    private Integer year;
    private String genre;
    private Integer quantity;
    private Set<Label> label;
    private Integer price;
    private Musician musicians;

    public ReleaseResponseDto(){

    }

    public ReleaseResponseDto(Release release){
        setVisible(release.getVisible());
        setId(release.getId());
        this.releaseName = release.getReleaseName();
        this.description = release.getDescription();
        this.imageUrl = release.getImageUrl();
        this.year = release.getYear();
        this.genre = release.getGenre();
        this.quantity = release.getQuantity();

        this.price = release.getPrice();

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<Label> getLabel() {
        return label;
    }

    public void setLabel(Set<Label> label) {
        this.label = label;
    }

    public Musician getMusicians() {
        return musicians;
    }

    public void setMusicians(Musician musicians) {
        this.musicians = musicians;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReleaseResponseDto{" +
                "releaseName='" + releaseName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", quantity=" + quantity +
                ", label=" + label +
                ", price=" + price +
                ", musicians=" + musicians +
                '}';
    }
}
