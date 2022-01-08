package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Customer;

import java.util.Map;

public interface CustomerService extends BaseService<Customer> {
    Map<Long, String> findAllProductsByCustomerId(Long customerId);
}
