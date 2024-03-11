package com.babelgroup.controllers;

import com.babelgroup.Application;
import com.babelgroup.dto.AccountDto;
import com.babelgroup.dto.OperationDto;
import com.babelgroup.exceptions.AccountNotFoundException;
import com.babelgroup.model.Operation;
import com.babelgroup.services.operations.IOperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private static final Logger logger = LogManager.getLogger(Application.class);
    private IOperationService operationService;

    @GetMapping("/list")
    public List<Operation> getOperations(@RequestParam String iban){
        return operationService.getOperations(iban);
    }

    @PostMapping("/add")
    public void addAccounts(@RequestParam OperationDto operation){
        try {
            operationService.addOperation(operation);
        } catch (AccountNotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}
