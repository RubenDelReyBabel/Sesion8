package com.babelgroup.repositories.clients;

import com.babelgroup.exceptions.ClientNotFoundException;
import com.babelgroup.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository {

    Client getClientByUsername(String username) throws ClientNotFoundException;

    Client getClientById(Long id) throws ClientNotFoundException;
}
