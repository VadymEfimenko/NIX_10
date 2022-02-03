package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.service.BaseService;

import java.util.Map;

public interface ReleaseService extends BaseService<Release> {
    Map<Long, String> findAllDistributorsByReleaseId(Long releaseId);
}
