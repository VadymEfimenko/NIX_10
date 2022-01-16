package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.dao.TransactionDao;
import ua.com.alevel.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    private final AccountDao accountDao;
    private final JpaConfig jpaConfig;
    @PersistenceContext
    EntityManager entityManager;

    public TransactionDaoImpl(AccountDao accountDao, JpaConfig jpaConfig) {
        this.accountDao = accountDao;
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Transaction entity) {
        entityManager.persist(entity);
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
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public List<Transaction> transactionsByAccount(Long id) {
        List<Transaction> transactions = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select * from transactions where account_id = " + id)) {
            while (resultSet.next()) {
                transactions.add(convertResultSetToTransaction(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }

    private Transaction convertResultSetToTransaction(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Long sum = resultSet.getLong("sum");
        Long accountId = resultSet.getLong("account_id");
        String category = resultSet.getString("category");
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setCreated(created);
        transaction.setSum(sum);
        transaction.setAccount(accountDao.findById(accountId));
        transaction.setCategory(category);
        return transaction;
    }
}
