package com.babelgroup.controllers;

import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.OperationDto;
import com.babelgroup.model.Operation;
import com.babelgroup.services.operations.IOperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private IOperationService operationService;

    @GetMapping("/list")
    public List<Operation> getOperations(@RequestParam String iban){
        return operationService.getOperations(iban);
    }

    @PostMapping("/add")
    public void addAccounts(@RequestParam OperationDto operation){
        operationService.addOperation(operation);
    }
}
