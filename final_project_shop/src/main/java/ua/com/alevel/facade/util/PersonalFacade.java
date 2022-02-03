package ua.com.alevel.facade.util;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.response.util.PageData;
import ua.com.alevel.view.dto.response.util.PersonalResponseDto;

public interface PersonalFacade {
    PersonalResponseDto findById(Long id);

    PageData<PersonalResponseDto> findAll(WebRequest request);
}
