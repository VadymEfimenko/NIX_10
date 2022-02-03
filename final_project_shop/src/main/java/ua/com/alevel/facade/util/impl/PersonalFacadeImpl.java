package ua.com.alevel.facade.util.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.util.PersonalFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.user.PersonalService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.response.util.PageData;
import ua.com.alevel.view.dto.response.util.PersonalResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalFacadeImpl implements PersonalFacade {

    private final PersonalService personalService;

    public PersonalFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public PersonalResponseDto findById(Long id) {
        return new PersonalResponseDto(personalService.findById(id).get());
    }

    @Override
    public PageData<PersonalResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Personal> tableResponse = personalService.findAll(dataTableRequest);
        List<PersonalResponseDto> personals = tableResponse.getItems().stream().
                map(PersonalResponseDto::new).
                collect(Collectors.toList());
        PageData<PersonalResponseDto> pageData = (PageData<PersonalResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(personals);
        return pageData;
    }
}
