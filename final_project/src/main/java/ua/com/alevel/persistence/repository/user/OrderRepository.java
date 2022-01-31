package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Order;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface OrderRepository extends BaseRepository<Order> {
}
