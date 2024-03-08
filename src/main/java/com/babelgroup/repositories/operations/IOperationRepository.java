package com.babelgroup.repositories.operations;

import com.babelgroup.dto.OperationDto;
import com.babelgroup.model.Account;
import com.babelgroup.model.Operation;

import java.util.List;

public interface IOperationRepository {

    void addOperation(Operation operation);

    List<Operation> getOperations(Account a);
}
