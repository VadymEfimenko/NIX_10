package ua.com.alevel.facade.util.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.util.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.user.PersonalService;
import ua.com.alevel.view.dto.request.registration.AuthDto;

import java.util.Date;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
        personal.setCreated(new Date(System.currentTimeMillis()));
        personal.setUpdated(new Date(System.currentTimeMillis()));
        personal.setEmail(dto.getEmail());
        personal.setPassword(dto.getPassword());
        personal.setFirstName(dto.getFirstName());
        personal.setLastName(dto.getLastName());
        personalService.create(personal);
    }
}
