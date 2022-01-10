package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Product;

import java.util.Map;

public interface ProductService extends BaseService<Product> {

    Map<Long, String> findAllCustomersByProductId(Long productId);
}
