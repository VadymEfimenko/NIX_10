package ua.com.alevel.persistence.repository;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
