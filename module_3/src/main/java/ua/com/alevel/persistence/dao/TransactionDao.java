package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Transaction;

import java.util.List;

public interface TransactionDao extends BaseDao<Transaction>{

    List<Transaction> findTransactionByAccountId(Long id);
}
