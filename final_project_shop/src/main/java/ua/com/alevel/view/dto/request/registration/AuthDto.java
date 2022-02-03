package ua.com.alevel.view.dto.request.registration;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDto {

    private String email;
    private String password;
    private String passwordConfirm;
    private String firstName;
    private String lastName;
}
