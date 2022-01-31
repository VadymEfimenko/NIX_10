package ua.com.alevel.facade.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.LabelFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.service.shop.LabelService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.LabelRequestDto;
import ua.com.alevel.view.dto.response.LabelResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LabelFacadeImpl implements LabelFacade {

    private final LabelService labelService;

    public LabelFacadeImpl(LabelService labelService) {
        this.labelService = labelService;
    }

    @Override
    public void create(LabelRequestDto labelRequestDto) {
        Label label = new Label();
        label.setLabelName(labelRequestDto.getLabelName());
        label.setCreated(new Date(System.currentTimeMillis()));
        label.setUpdated(new Date(System.currentTimeMillis()));
        labelService.create(label);
    }

    @Override
    public void update(LabelRequestDto labelRequestDto, Long id) {
        Label label = labelService.findById(id).get();
        label.setLabelName(labelRequestDto.getLabelName());
        label.setUpdated(new Date(System.currentTimeMillis()));
        labelService.update(label);
    }

    @Override
    public void delete(Long id) {
        labelService.delete(id);
    }

    @Override
    public LabelResponseDto findById(Long id) {
        return new LabelResponseDto(labelService.findById(id).get());
    }

    @Override
    public PageData<LabelResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Label> labelDataTableResponse = labelService.findAll(dataTableRequest);
        List<LabelResponseDto> labelResponseDtos = labelDataTableResponse.getItems().stream()
                .map(LabelResponseDto::new)
                .collect(Collectors.toList());
        PageData<LabelResponseDto> pageData = (PageData<LabelResponseDto>) WebResponseUtil.initPageData(labelDataTableResponse);
        pageData.setItems(labelResponseDtos);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllReleasesByLabelId(Long labelId) {
        return labelService.findAllReleasesByLabelId(labelId);
    }

    @Override
    public List<Label> findAll() {
        return labelService.findAll();
    }
}
