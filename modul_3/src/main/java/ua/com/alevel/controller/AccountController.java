package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.AccountRequestDto;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.facade.UserFacade;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final UserFacade userFacade;
    private final TransactionFacade transactionFacade;
    private final AccountFacade accountFacade;

    public AccountController(UserFacade userFacade, TransactionFacade transactionFacade, AccountFacade accountFacade) {
        this.userFacade = userFacade;
        this.transactionFacade = transactionFacade;
        this.accountFacade = accountFacade;
    }

    @GetMapping("/new/{id}")
    public String redirectToNewAccountPage(@PathVariable Long id, Model model) {
        AccountRequestDto accountRequestDto = new AccountRequestDto();
        accountRequestDto.setUserId(id);
        model.addAttribute("account", accountRequestDto);
        model.addAttribute("userId", id);
        return "pages/account/account_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("account") AccountRequestDto dto) {
        accountFacade.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountFacade.findById(id));
        model.addAttribute("user", accountFacade.findUserByAccountId(id));
        return "pages/account/account_details";
    }

    @GetMapping("history/{id}")
    public String historyTransactions(@PathVariable Long id) {
        transactionFacade.createTransactionHistory(id);
        return "redirect:/users";
    }
}
