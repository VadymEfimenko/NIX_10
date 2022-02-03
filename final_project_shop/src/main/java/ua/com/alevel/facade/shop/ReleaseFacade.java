package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.util.BaseFacade;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.view.dto.request.shop.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.shop.ReleaseResponseDto;

import java.util.Map;

public interface ReleaseFacade extends BaseFacade<ReleaseRequestDto, ReleaseResponseDto> {
    Map<Long, String> findAllDistributorsByReleaseId(Long releaseId);

    Release findReleaseById(Long id);
}
