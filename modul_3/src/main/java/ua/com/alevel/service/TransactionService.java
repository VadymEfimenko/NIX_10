package ua.com.alevel.service;

import ua.com.alevel.entity.Transaction;

public interface TransactionService extends BaseService<Transaction> {

    void createTransactionHistory(Long id);
    void makeTransaction(Long sum, Long accountId);
}
