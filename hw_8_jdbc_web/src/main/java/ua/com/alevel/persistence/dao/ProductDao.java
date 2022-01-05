package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao extends BaseDao<Product> {

    Map<Long, String> findAllByCustomerId(Long customerId);
}
