package com.babelgroup.repositories.accounts;

import com.babelgroup.Application;
import com.babelgroup.exceptions.AccountNotFoundException;
import com.babelgroup.model.Account;
import com.babelgroup.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {
    private List<Account> accountList;
    @Override
    public List<Account> getAccounts(Client client) {
        init();
        return accountList.stream().filter(a -> a.getClient().equals(client)).toList();
    }

    @Override
    public void addAccount(Account account) {
        init();
        accountList.add(account);
    }

    @Override
    public Account getAccountByIban(String iban) throws AccountNotFoundException {
        List<Account> foundAccounts = accountList.stream().filter(a -> a.getIban().equals(iban)).toList();
        if (foundAccounts.isEmpty()){
            throw new AccountNotFoundException(String.format("Account not found for IBAN %s", iban));
        }
        return foundAccounts.get(0);
    }

    private void init(){
        if (accountList == null){
            accountList = new ArrayList<>();
            Client client = new Client();
            client.setUsername("username");
            client.setName("name");
            client.setSurname("surname");

            Account account = new Account();
            account.setIban("IBAN1");
            account.setMoney(100);
            account.setClient(client);
            account.setOperationList(new ArrayList<>());

            accountList.add(account);
        }
    }
}
