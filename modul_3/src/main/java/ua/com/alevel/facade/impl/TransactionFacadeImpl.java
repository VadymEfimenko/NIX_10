package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.TransactionRequestDto;
import ua.com.alevel.dto.response.TransactionResponseDto;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.service.TransactionService;

import java.util.Date;
import java.util.List;

@Service
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    public TransactionFacadeImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void create(TransactionRequestDto transactionRequestDto) {
        Account account = transactionRequestDto.getAccount();
        Long sum = transactionRequestDto.getSum();
        transactionService.makeTransaction(sum, account.getId());
        Transaction transaction = new Transaction();
        transaction.setCreated(new Date(System.currentTimeMillis()));
        transaction.setCategory(transactionRequestDto.getCategory());
        transaction.setSum(sum);
        transaction.setAccount(account);
        transactionService.create(transaction);
    }

    @Override
    public void delete(Long id) {}

    @Override
    public TransactionResponseDto findById(Long id) {
        return null;
    }

    @Override
    public List<TransactionResponseDto> findAll() {
        return null;
    }

    @Override
    public void createTransactionHistory(Long id) {
        transactionService.createTransactionHistory(id);
    }
}
