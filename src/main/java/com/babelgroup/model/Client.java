package com.babelgroup.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Client {

    private Long id;
    private String username;
    private String name;
    private String surname;
}
