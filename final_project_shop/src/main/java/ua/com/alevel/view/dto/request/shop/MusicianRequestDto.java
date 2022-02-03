package ua.com.alevel.view.dto.request.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.enums.MusicianGroup;
import ua.com.alevel.view.dto.request.util.RequestDto;

@Getter
@Setter
@ToString
public class MusicianRequestDto extends RequestDto {

    private String name;
    private MusicianGroup musicianGroup;
}
