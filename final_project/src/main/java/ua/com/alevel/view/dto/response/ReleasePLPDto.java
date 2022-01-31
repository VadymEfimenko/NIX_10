package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.release.Release;

import java.util.Set;

public class ReleasePLPDto extends ResponseDto {

    private String releaseName;
    private String description;
    private String imageUrl;
    private Integer year;
    private String genre;
    private Integer price;
    private Musician musicians;

    public ReleasePLPDto(){

    }

    public ReleasePLPDto(Release release){
        setId(release.getId());
        setCreated(release.getCreated());
        setUpdated(release.getUpdated());
        setVisible(release.getVisible());
        this.releaseName = release.getReleaseName();
        this.description = release.getDescription();
        this.imageUrl = release.getImageUrl();
        this.year = release.getYear();
        this.genre = release.getGenre();
        this.price = release.getPrice();
        this.musicians = release.getMusician();
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

    public Musician getMusicians() {
        return musicians;
    }

    public void setMusicians(Musician musicians) {
        this.musicians = musicians;
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

    @Override
    public String toString() {
        return "ReleasePLPDto{" +
                "releaseName='" + releaseName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", musicians=" + musicians +
                '}';
    }
}
