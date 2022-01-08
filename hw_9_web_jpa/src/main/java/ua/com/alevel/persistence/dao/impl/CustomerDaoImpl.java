package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CustomerDao;
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

@Service
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Customer entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Customer entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Customer c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(c.id) from Customer c where c.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public DataTableResponse<Customer> findAll(DataTableRequest dataTableRequest) {
        int firstResult = (dataTableRequest.getCurrentPage() - 1) * dataTableRequest.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> from = criteriaQuery.from(Customer.class);
        if (dataTableRequest.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(dataTableRequest.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(dataTableRequest.getSort())));
        }
        List<Customer> customers = entityManager.createQuery(criteriaQuery)
                .setFirstResult(firstResult)
                .setMaxResults(dataTableRequest.getPageSize())
                .getResultList();
        DataTableResponse<Customer> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(customers);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Customer ");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findAllProductsByCustomerId(Long id) {
        Map<Long, String> map = new HashMap<>();
        Set<Product> products = findById(id).getProducts();
        for (Product product : products) {
            map.put(product.getId(), product.getName());
        }
        return map;
    }
}
