package ua.com.alevel.facade.shop.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.shop.DistributorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.service.shop.DistributorService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.shop.DistributorRequestDto;
import ua.com.alevel.view.dto.response.shop.DistributorResponseDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DistributorFacadeImpl implements DistributorFacade {

    private final DistributorService distributorService;

    public DistributorFacadeImpl(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @Override
    public void create(DistributorRequestDto distributorRequestDto) {
        Distributor distributor = new Distributor();
        distributor.setCreated(new Date(System.currentTimeMillis()));
        distributor.setUpdated(new Date(System.currentTimeMillis()));
        distributor.setName(distributorRequestDto.getName());
        distributor.setWebsite(distributorRequestDto.getWebsite());
        distributor.setDigitalMedia(distributorRequestDto.getDigitalMedia());
        distributor.setFormat(distributorRequestDto.getFormat());
        distributorService.create(distributor);
    }

    @Override
    public void update(DistributorRequestDto distributorRequestDto, Long id) {
        Distributor distributor = distributorService.findById(id).get();
        distributor.setName(distributorRequestDto.getName());
        distributor.setWebsite(distributorRequestDto.getWebsite());
        distributor.setDigitalMedia(distributorRequestDto.getDigitalMedia());
        distributor.setFormat(distributorRequestDto.getFormat());
        distributor.setUpdated(new Date(System.currentTimeMillis()));
        distributorService.update(distributor);
    }

    @Override
    public void delete(Long id) {
        distributorService.delete(id);
    }

    @Override
    public DistributorResponseDto findById(Long id) {
        return new DistributorResponseDto(distributorService.findById(id).get());
    }

    @Override
    public PageData<DistributorResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Distributor> response = distributorService.findAll(dataTableRequest);
        List<DistributorResponseDto> distributors = response.getItems().stream()
                .map(DistributorResponseDto::new)
                .collect(Collectors.toList());
        PageData<DistributorResponseDto> pageData = (PageData<DistributorResponseDto>) WebResponseUtil.initPageData(response);
        pageData.setItems(distributors);
        return pageData;
    }

    @Override
    public Map<Long, String> findAllReleasesByDistributorId(Long distributorId) {
        return distributorService.findAllReleasesByDistributorId(distributorId);
    }

    @Override
    public List<Distributor> findAll() {
        return distributorService.findAll();
    }
}
