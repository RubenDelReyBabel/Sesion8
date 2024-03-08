package com.babelgroup.services.operations;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.OperationDto;
import com.babelgroup.model.Account;
import com.babelgroup.model.Operation;
import com.babelgroup.repositories.accounts.IAccountRepository;
import com.babelgroup.repositories.operations.IOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService implements IOperationService{

    private IOperationRepository operationRepository;
    private IAccountRepository accountRepository;

    public OperationService(IOperationRepository operationRepository, IAccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addOperation(OperationDto operation) {
        Operation o = new Operation();
        Account from = accountRepository.getAccountByIban(operation.from);
        Account to = accountRepository.getAccountByIban(operation.to);
        o.setFrom(from);
        o.setTo(to);
        o.setAmount(operation.amount);

        operationRepository.addOperation(o);

        if (getBank(to.getIban()).equals(getBank(from.getIban()))){
            Operation interest = new Operation();
            o.setFrom(from);
            Account bank = accountRepository.getAccountByIban("IBAN-BANCO");
            o.setTo(bank);
            o.setAmount(3.99);

            operationRepository.addOperation(interest);
        }
    }

    @Override
    public List<Operation> getOperations(String iban) {
        Account a = accountRepository.getAccountByIban(iban);

        return operationRepository.getOperations(a);
    }

    private String getBank(String iban){
        return iban.substring(4, 7);
    }
}
