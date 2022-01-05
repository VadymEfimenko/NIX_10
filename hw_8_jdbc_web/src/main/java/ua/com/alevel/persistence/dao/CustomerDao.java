package ua.com.alevel.persistence.dao;

import org.attoparser.dom.INestableNode;
import ua.com.alevel.persistence.entity.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerDao extends BaseDao<Customer> {

    Map<Long, String> findAllByProductId(Long productId);
    void createBinding(Customer customer, List<Integer> productsId);
}
