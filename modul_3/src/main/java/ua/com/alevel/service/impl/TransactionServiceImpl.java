package ua.com.alevel.service.impl;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.AccountDao;
import ua.com.alevel.dao.TransactionDao;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.service.TransactionService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionServiceImpl(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @Override
    public void create(Transaction entity) {
        transactionDao.create(entity);
    }

    @Override
    public void delete(Long id) {
        transactionDao.delete(id);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public void createTransactionHistory(Long id) {
        List<Transaction> transactions = transactionDao.transactionsByAccount(id);
        String csv = "TransactionsByAccount" + id + ".csv";
        File file = new File(csv);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            for (Transaction transaction : transactions) {
                String values[] = {transaction.getAccount().getUser().getName(), transaction.getCategory(),
                        String.valueOf(transaction.getSum()), isPositive(transaction.getSum()) ? "income" : "outcome"};
                writer.writeNext(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeTransaction(Long sum, Long accountId) {
        Account account = accountDao.findById(accountId);
        Long balance = account.getBalance();
        if (sum == 0) {
            throw new RuntimeException("Zero transaction is invalid");
        } else {
            account.setBalance(balance + sum);
            accountDao.update(account);
        }

    }

    private boolean isPositive(Long sum){
        if (sum > 0){
            return true;
        }
        return false;
    }
}
