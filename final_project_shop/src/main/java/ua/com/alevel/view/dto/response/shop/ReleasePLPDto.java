package ua.com.alevel.view.dto.response.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.view.dto.response.util.ResponseDto;

@Getter
@Setter
@ToString
public class ReleasePLPDto extends ResponseDto {

    private String name;
    private Musician musician;
    private Integer price;
    private String imageUrl;
    private String description;

    public ReleasePLPDto() {
    }

    public ReleasePLPDto(Release release) {
        setId(release.getId());
        setCreated(release.getCreated());
        setUpdated(release.getUpdated());
        setVisible(release.getVisible());
        this.name = release.getName();
        this.musician = release.getMusician();
        this.price = release.getPrice();
        this.imageUrl = release.getImage();
        this.description = release.getDescription();
    }
}
