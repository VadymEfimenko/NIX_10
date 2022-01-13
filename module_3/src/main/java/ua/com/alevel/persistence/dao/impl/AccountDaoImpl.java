package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

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
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByAccountId(Long accountId) {
        return findById(accountId).getUser();
    }

    @Override
    public void create(Account entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Account entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.contains(findById(id));
    }

    @Override
    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> from = criteriaQuery.from(Account.class);
        if (request.getOrder().equals("asc")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        }
        List<Account> accounts = entityManager.createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Account> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(accounts);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Account");
        return (Long) query.getSingleResult();
    }
}
