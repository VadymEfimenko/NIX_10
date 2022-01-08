package ua.com.alevel.persistence.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CustomerDao;
import ua.com.alevel.persistence.dao.ProductDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Customer;
import ua.com.alevel.persistence.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductDaoImpl implements ProductDao {

    @Autowired
    CustomerDao customerDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Product entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Product entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        Product product = entityManager.find(Product.class, id);
        List<Customer> customers = product.getCustomers().stream().filter(customer -> customerDao.findAllProductsByCustomerId(customer.getId()).size() == 1).collect(Collectors.toList());
        product.getCustomers().retainAll(customers);
        entityManager.remove(product);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(p.id) from Product p where p.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public DataTableResponse<Product> findAll(DataTableRequest dataTableRequest) {
        int firstResult = (dataTableRequest.getCurrentPage() - 1) * dataTableRequest.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> from = criteriaQuery.from(Product.class);
        if (dataTableRequest.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(dataTableRequest.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(dataTableRequest.getSort())));
        }
        List<Product> products = entityManager.createQuery(criteriaQuery)
                .setFirstResult(firstResult)
                .setMaxResults(dataTableRequest.getPageSize())
                .getResultList();
        DataTableResponse<Product> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(products);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Product");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllCustomersByProductId(Long productId) {
        Map<Long, String> map = new HashMap<>();
        Set<Customer> customers = findById(productId).getCustomers();
        for (Customer customer : customers) {
            map.put(customer.getId(), customer.getFirstName() + " " + customer.getSecondName());
        }
        return map;
    }
}
