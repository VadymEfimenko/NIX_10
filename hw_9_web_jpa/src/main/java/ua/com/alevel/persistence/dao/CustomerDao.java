package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Customer;

import java.util.Map;

public interface CustomerDao extends BaseDao<Customer> {

    Map<Long, String> findAllProductsByCustomerId(Long id);
}
