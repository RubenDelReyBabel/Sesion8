package com.babelgroup.services.accounts;

import com.babelgroup.Application;
import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.ClientDto;
import com.babelgroup.exceptions.ClientNotFoundException;
import com.babelgroup.model.Account;
import com.babelgroup.model.Client;
import com.babelgroup.repositories.clients.IClientRepository;
import com.babelgroup.repositories.accounts.IAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService{

    private static final Logger logger = LogManager.getLogger(Application.class);

    private final IAccountRepository accountRepository;
    private final IClientRepository clientRepository;

    public AccountService(IAccountRepository accountRepository, IClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Account> getAccounts(String username) {
        Client c = null;
        List<Account> accountList = new ArrayList<>();
        try {
            c = clientRepository.getClientByUsername(username);
            accountList = accountRepository.getAccounts(c);
        } catch (ClientNotFoundException e) {
            logger.error(e.getMessage());
        }


        logger.info(String.format("%x accounts have been retrieved for user %s",
                accountList.size(),
                username));

        return accountList;
    }

    @Override
    public void addAccount(AccountDto account) throws ClientNotFoundException {
        Client client = clientRepository.getClientById(account.client);

        Account acc = new Account();
        acc.setIban(account.iban);
        acc.setMoney(account.money);
        acc.setClient(client);

        logger.info(String.format("An account for client %s has being created IBAN - %s",
                client.getUsername(),
                acc.getIban()));

        accountRepository.addAccount(acc);
    }
}
