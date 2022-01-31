package ua.com.alevel.facade.shop;

import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.view.dto.request.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.ReleaseResponseDto;

import java.util.Map;

public interface ReleaseFacade extends BaseFacade<ReleaseRequestDto, ReleaseResponseDto> {
    Map<Long, String> findAllLabelsByReleaseId(Long releaseId);
    Release findReleaseById(Long id);
}
