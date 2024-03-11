package com.babelgroup.services.accounts;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.exceptions.ClientNotFoundException;
import com.babelgroup.model.Account;
import com.babelgroup.model.Client;

import java.util.List;

public interface IAccountService {

    List<Account> getAccounts(String username);

    void addAccount(AccountDto account) throws ClientNotFoundException;
}
