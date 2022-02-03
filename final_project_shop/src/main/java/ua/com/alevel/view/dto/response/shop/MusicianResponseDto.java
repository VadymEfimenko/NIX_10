package ua.com.alevel.view.dto.response.shop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.shop.Musician;
import ua.com.alevel.persistence.enums.MusicianGroup;
import ua.com.alevel.view.dto.response.util.ResponseDto;

@Getter
@Setter
@ToString
public class MusicianResponseDto extends ResponseDto {

    private String name;
    private MusicianGroup musicianGroup;
    private Integer releasesCount;


    public MusicianResponseDto() {
    }

    public MusicianResponseDto(Musician musician) {
        setId(musician.getId());
        setCreated(musician.getCreated());
        setUpdated(musician.getUpdated());
        setVisible(true);
        this.name = musician.getName();
        this.musicianGroup = musician.getMusicianGroup();
        this.releasesCount = musician.getReleases().size();
    }
}
