package com.babelgroup.services.operations;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.OperationDto;
import com.babelgroup.model.Operation;

import java.util.List;

public interface IOperationService {

    void addOperation(OperationDto operation);
    List<Operation> getOperations(String iban);
}
