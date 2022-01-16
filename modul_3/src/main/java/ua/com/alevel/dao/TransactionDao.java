package ua.com.alevel.dao;

import ua.com.alevel.entity.Transaction;

import java.util.List;

public interface TransactionDao extends BaseDao<Transaction> {

    List<Transaction> transactionsByAccount(Long id);
}
