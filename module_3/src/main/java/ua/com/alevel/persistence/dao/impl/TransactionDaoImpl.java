package ua.com.alevel.persistence.dao.impl;


import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Transaction entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Transaction entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public Transaction findById(Long id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> from = criteriaQuery.from(Transaction.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Transaction> transactions = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Transaction> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(transactions);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Transaction ");
        return (Long) query.getSingleResult();
    }

    @Override
    public List<Transaction> findTransactionByAccountId(Long id) {
        return entityManager.createQuery("select t from Transaction as t where t.account = :accountId", Transaction.class)
                .setParameter("accountId", id).getResultList();
    }
}
