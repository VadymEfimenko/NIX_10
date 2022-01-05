package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerService extends BaseService<Customer> {

    Map<Long, String> findAllByProductId(Long productId);
    void createBinding(Customer customer, List<Integer> productsId);
}
