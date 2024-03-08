package com.babelgroup.controllers;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.model.Account;
import com.babelgroup.services.accounts.IAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public List<Account> getAccounts(@RequestParam String username){
        return accountService.getAccounts(username);
    }

    @PostMapping("/add")
    public void addAccounts(@RequestParam AccountDto account){
        accountService.addAccount(account);
    }


}
