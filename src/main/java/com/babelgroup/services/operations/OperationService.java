package com.babelgroup.services.operations;

import com.babelgroup.Application;
import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.OperationDto;
import com.babelgroup.exceptions.AccountNotFoundException;
import com.babelgroup.model.Account;
import com.babelgroup.model.Operation;
import com.babelgroup.repositories.accounts.IAccountRepository;
import com.babelgroup.repositories.operations.IOperationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationService implements IOperationService{

    private static final Logger logger = LogManager.getLogger(Application.class);

    private IOperationRepository operationRepository;
    private IAccountRepository accountRepository;

    public OperationService(IOperationRepository operationRepository, IAccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addOperation(OperationDto operation) throws AccountNotFoundException {
        Operation o = new Operation();
        Account from = accountRepository.getAccountByIban(operation.from);
        Account to = accountRepository.getAccountByIban(operation.to);
        o.setFrom(from);
        o.setTo(to);
        o.setAmount(operation.amount);

        operationRepository.addOperation(o);

        logger.info(String.format("Operation from account %s to account %s with an amount of %f has been made",
                from.getIban(),
                to.getIban(),
                o.getAmount()));

        if (getBank(to.getIban()).equals(getBank(from.getIban()))){
            Operation interest = new Operation();
            o.setFrom(from);
            Account bank = accountRepository.getAccountByIban("IBAN-BANCO");
            o.setTo(bank);
            o.setAmount(3.99);

            logger.info(String.format("An interest %f of has been applied",
                    3.99));

            operationRepository.addOperation(interest);
        }
    }

    @Override
    public List<Operation> getOperations(String iban) {
        Account a = null;
        List<Operation> operationList = new ArrayList<>();
        try {
            a = accountRepository.getAccountByIban(iban);
            operationList = operationRepository.getOperations(a);
        } catch (AccountNotFoundException e) {
            logger.error(e.getMessage());
        }
        logger.info(String.format("%x operation have been retrieved for account %s",
                operationList.size(),
                iban));

        return operationList;
    }

    private String getBank(String iban){
        return iban.substring(4, 7);
    }
}
