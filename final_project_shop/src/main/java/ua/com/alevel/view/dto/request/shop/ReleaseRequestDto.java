package ua.com.alevel.view.dto.request.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.enums.ReleaseType;
import ua.com.alevel.view.dto.request.util.RequestDto;

import java.util.Set;

@Getter
@Setter
@ToString
public class ReleaseRequestDto extends RequestDto {

    private String releaseName;
    private Integer price;
    private Integer quantity;
    private String description;
    private String imageUrl;
    private String distributorName;
    private Set<Long> distributorsId;
    private Long musicianId;
    private ReleaseType releaseType;
}
