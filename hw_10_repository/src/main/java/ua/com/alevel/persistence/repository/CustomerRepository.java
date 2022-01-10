package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Customer;

@Repository
public interface CustomerRepository extends BaseRepository<Customer> {
}
