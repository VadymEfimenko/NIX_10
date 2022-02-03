package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.util.BaseFacade;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.view.dto.request.shop.DistributorRequestDto;
import ua.com.alevel.view.dto.response.shop.DistributorResponseDto;

import java.util.List;
import java.util.Map;

public interface DistributorFacade extends BaseFacade<DistributorRequestDto, DistributorResponseDto> {

    Map<Long, String> findAllReleasesByDistributorId(Long distributorId);

    List<Distributor> findAll();
}
