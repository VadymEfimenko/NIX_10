package ua.com.alevel.view.dto.request;

public class MusicianRequestDto extends RequestDto {

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "MusicianRequestDto{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
