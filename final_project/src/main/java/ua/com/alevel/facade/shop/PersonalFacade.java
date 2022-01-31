package ua.com.alevel.facade.shop;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

public interface PersonalFacade {
    PersonalResponseDto findById(Long id);

    PageData<PersonalResponseDto> findAll(WebRequest request);
}
