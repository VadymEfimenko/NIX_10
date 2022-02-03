package ua.com.alevel.service.shop;

import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.service.BaseService;

import java.util.List;
import java.util.Map;

public interface DistributorService extends BaseService<Distributor> {

    Map<Long, String> findAllReleasesByDistributorId(Long distributorId);

    List<Distributor> findAll();
}
