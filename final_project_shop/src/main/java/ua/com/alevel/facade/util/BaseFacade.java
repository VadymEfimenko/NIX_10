package ua.com.alevel.facade.util;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.request.util.RequestDto;
import ua.com.alevel.view.dto.response.util.PageData;
import ua.com.alevel.view.dto.response.util.ResponseDto;

public interface BaseFacade<REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);

    void update(REQ req, Long id);

    void delete(Long id);

    RES findById(Long id);

    PageData<RES> findAll(WebRequest request);
}
