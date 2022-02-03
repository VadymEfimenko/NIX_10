package ua.com.alevel.view.dto.response.util;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.Personal;

@Getter
@Setter
public class PersonalResponseDto extends ResponseDto{
    private String email;
    private String fullName;

    public PersonalResponseDto(Personal personal){
        setId(personal.getId());
        setCreated(personal.getCreated());
        setUpdated(personal.getUpdated());
        setVisible(personal.getEnabled());
        this.email = personal.getEmail();
        this.fullName = personal.getFullName();
    }
}
