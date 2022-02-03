package ua.com.alevel.view.dto.response.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.enums.ReleaseType;
import ua.com.alevel.view.dto.response.util.ResponseDto;

import java.util.Set;

@Getter
@Setter
@ToString
public class ReleaseResponseDto extends ResponseDto {

    private String name;
    private Integer price;
    private String description;
    private String image;
    private Integer quantity;
    private Integer distributorCount;
    private ReleaseType releaseType;
    private Set<Distributor> distributors;
    private Musician musician;

    public ReleaseResponseDto() {
    }

    public ReleaseResponseDto(Release release) {
        setId(release.getId());
        setCreated(release.getCreated());
        setUpdated(release.getUpdated());
        this.name = release.getName();
        this.price = release.getPrice();
        this.description = release.getDescription();
        this.image = release.getImage();
        this.quantity = release.getQuantity();
        this.distributorCount = release.getDistributors().size();
        this.releaseType = release.getReleaseType();
        this.distributors = release.getDistributors();
        this.musician = release.getMusician();
    }
}
