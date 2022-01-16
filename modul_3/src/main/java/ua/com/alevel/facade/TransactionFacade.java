package ua.com.alevel.facade;

import ua.com.alevel.dto.request.TransactionRequestDto;
import ua.com.alevel.dto.response.TransactionResponseDto;
import ua.com.alevel.entity.Transaction;

public interface TransactionFacade extends BaseFacade<TransactionRequestDto, TransactionResponseDto>{

    void createTransactionHistory(Long id);
}
