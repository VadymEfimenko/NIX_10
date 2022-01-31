package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.view.dto.request.LabelRequestDto;
import ua.com.alevel.view.dto.response.LabelResponseDto;

import java.util.List;
import java.util.Map;

public interface LabelFacade extends BaseFacade<LabelRequestDto, LabelResponseDto> {
    Map<Long, String> findAllReleasesByLabelId(Long labelId);

    List<Label> findAll();
}
