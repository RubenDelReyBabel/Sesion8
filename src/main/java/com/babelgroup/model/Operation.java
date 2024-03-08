package com.babelgroup.model;

import lombok.Data;

@Data
public class Operation {

    private Account from;
    private Account to;
    private double amount;
}
