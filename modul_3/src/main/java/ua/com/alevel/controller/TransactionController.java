package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.TransactionRequestDto;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.service.AccountService;

import java.sql.SQLException;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionFacade transactionFacade;
    private final AccountService accountService;
    private Long accountId = 0L;

    public TransactionController(TransactionFacade transactionFacade, AccountService accountService) {
        this.transactionFacade = transactionFacade;
        this.accountService = accountService;
    }

    @GetMapping("/new/{id}")
    public String makeTransaction(@PathVariable Long id, Model model) {
        accountId = id;
        model.addAttribute("transaction", new TransactionRequestDto());
        return "pages/transaction/transaction_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("transaction") TransactionRequestDto dto) throws SQLException {
        dto.setAccount(accountService.findById(accountId));
        transactionFacade.create(dto);
        return "redirect:/accounts/details/" + accountId;
    }
}
