package com.babelgroup.repositories.accounts;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.model.Account;
import com.babelgroup.model.Client;

import java.util.List;

public interface IAccountRepository {

    List<Account> getAccounts(Client client);

    void addAccount(Account account);

    Account getAccountByIban(String iban);
}
