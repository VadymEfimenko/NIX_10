package ua.com.alevel.persistence.dao.impl;


import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Transaction;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {
    @Override
    public void create(Transaction entity) {

    }

    @Override
    public void update(Transaction entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Transaction> findTransactionByAccountId(Long id) {
        return null;
    }
}
