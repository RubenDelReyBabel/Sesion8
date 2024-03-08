package com.babelgroup.services.accounts;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.model.Account;
import com.babelgroup.model.Client;
import com.babelgroup.repositories.clients.IClientRepository;
import com.babelgroup.repositories.accounts.IAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService{

    private final IAccountRepository accountRepository;
    private final IClientRepository clientRepository;

    public AccountService(IAccountRepository accountRepository, IClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Account> getAccounts(String username) {
        Client c = clientRepository.getClientByUsername(username);

        return accountRepository.getAccounts(c);
    }

    @Override
    public void addAccount(AccountDto account) {
        Client client = clientRepository.getClientById(account.client);

        Account acc = new Account();
        acc.setIban(account.iban);
        acc.setMoney(account.money);
        acc.setClient(client);

        accountRepository.addAccount(acc);
    }
}
