package com.babelgroup.repositories.clients;

import com.babelgroup.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository {

    Client getClientByUsername(String username);

    Client getClientById(Long id);
}
