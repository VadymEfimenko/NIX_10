package ua.com.alevel.view.dto.request.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.view.dto.request.util.RequestDto;

@Getter
@Setter
@ToString
public class DistributorRequestDto extends RequestDto {

    private String name;
    private String website;
    private String digitalMedia;
    private String format;
}
