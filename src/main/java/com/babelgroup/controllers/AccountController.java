package com.babelgroup.controllers;

import com.babelgroup.Application;
import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.exceptions.ClientNotFoundException;
import com.babelgroup.model.Account;
import com.babelgroup.services.accounts.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(Application.class);
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
        try {
            accountService.addAccount(account);
        } catch (ClientNotFoundException e) {
            logger.error(e.getMessage());
        }
    }


}
