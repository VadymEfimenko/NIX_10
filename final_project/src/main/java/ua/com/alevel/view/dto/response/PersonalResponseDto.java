package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.user.Personal;

public class PersonalResponseDto extends ResponseDto {
    private String email;
    private String fullName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PersonalResponseDto(Personal personal) {
        setId(personal.getId());
        setCreated(personal.getCreated());
        setUpdated(personal.getUpdated());
        setVisible(personal.getEnabled());
        this.email = personal.getEmail();
        this.fullName = personal.getFullName();
    }
}
