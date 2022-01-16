package ua.com.alevel.dto.response;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.User;

public class UserResponseDto extends ResponseDto {

    private String name;

    public UserResponseDto(User user){
        this.name = (user.getName());
        setId(user.getId());
        setCreated(user.getCreated());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
