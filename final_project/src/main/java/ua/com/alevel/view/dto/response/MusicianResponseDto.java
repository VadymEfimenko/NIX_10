package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.musician.Musician;
import ua.com.alevel.persistence.entity.release.Release;

import java.util.Set;

public class MusicianResponseDto extends ResponseDto {

    private String nickName;
    private Integer releasesCount;

    public MusicianResponseDto(){

    }

    public  MusicianResponseDto(Musician musician){
        this.nickName = musician.getNickName();
        this.releasesCount = musician.getReleases().size();
        setId(musician.getId());
        setCreated(musician.getCreated());
        setUpdated(musician.getUpdated());
        setVisible(true);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getReleasesCount() {
        return releasesCount;
    }

    public void setReleasesCount(Integer releasesCount) {
        this.releasesCount = releasesCount;
    }

    @Override
    public String toString() {
        return "MusicianResponseDto{" +
                "nickName='" + nickName + '\'' +
                ", releasesCount=" + releasesCount +
                '}';
    }
}
