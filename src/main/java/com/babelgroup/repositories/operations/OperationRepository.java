package com.babelgroup.repositories.operations;

import com.babelgroup.dto.OperationDto;
import com.babelgroup.model.Account;
import com.babelgroup.model.Operation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationRepository implements IOperationRepository {

    private List<Operation> operationList;

    @Override
    public void addOperation(Operation operation) {
        init();
        operationList.add(operation);
    }

    @Override
    public List<Operation> getOperations(Account account) {
        init();
        return operationList.stream().filter(o -> o.getFrom().equals(account) || o.getTo().equals(account)).toList();
    }

    private void init(){
        if (operationList == null){
            operationList = new ArrayList<>();
        }
    }
}
