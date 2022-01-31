package ua.com.alevel.view.dto.request;

import java.util.Set;

public class ReleaseRequestDto extends RequestDto {

    private String releaseName;
    private Integer price;
    private String description;
    private String imageUrl;
    private Integer year;
    private String genre;
    private Integer quantity;
    private Long musician;
    private Set<Long> labelId;

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Long getMusician() {
        return musician;
    }

    public void setMusician(Long musician) {
        this.musician = musician;
    }

    public Set<Long> getLabelId() {
        return labelId;
    }

    public void setLabelId(Set<Long> labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "ReleaseRequestDto{" +
                "releaseName='" + releaseName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", quantity=" + quantity +
                ", musician=" + musician +
                ", labelId=" + labelId +
                '}';
    }
}
