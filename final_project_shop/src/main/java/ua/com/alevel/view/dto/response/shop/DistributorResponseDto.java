package ua.com.alevel.view.dto.response.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.view.dto.response.util.ResponseDto;

@Getter
@Setter
@ToString
public class DistributorResponseDto extends ResponseDto {

    private String name;
    private String website;
    private String digitalMedia;
    private String format;
    private Integer releaseCount;

    public DistributorResponseDto() {
    }

    public DistributorResponseDto(Distributor distributor) {
        setId(distributor.getId());
        setCreated(distributor.getCreated());
        setUpdated(distributor.getUpdated());
        this.name = distributor.getName();
        this.website = distributor.getWebsite();
        this.digitalMedia = distributor.getDigitalMedia();
        this.format = distributor.getFormat();
        this.releaseCount = distributor.getReleases().size();
    }
}
