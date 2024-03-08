package com.babelgroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Account {

    private String iban;
    private double money;

    private Client client;
    private List<Operation> operationList;
}
