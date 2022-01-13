package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
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
    public Transaction findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public void getTransactionsHistory(Long id) {

    }
}
