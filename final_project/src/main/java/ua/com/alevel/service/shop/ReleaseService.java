package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.service.BaseService;

import java.util.Map;

public interface ReleaseService extends BaseService<Release> {
    Map<Long, String> findAllLabelsByReleaseId(Long releaseId);

}
