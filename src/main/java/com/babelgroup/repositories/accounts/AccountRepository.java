package com.babelgroup.repositories.accounts;

import com.babelgroup.model.Account;
import com.babelgroup.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    private List<Account> accountList;
    @Override
    public List<Account> getAccounts(Client client) {
        init();
        return accountList;
    }

    @Override
    public void addAccount(Account account) {
        init();
        accountList.add(account);
    }

    @Override
    public Account getAccountByIban(String iban) {
        return accountList.stream().filter(a -> a.getIban().equals(iban)).toList().get(0);
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
