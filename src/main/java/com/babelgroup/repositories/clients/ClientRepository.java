package com.babelgroup.repositories.clients;

import com.babelgroup.exceptions.ClientNotFoundException;
import com.babelgroup.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository implements IClientRepository{

    private List<Client> clients;

    @Override
    public Client getClientByUsername(String username) throws ClientNotFoundException {
        init();
        List<Client> foundClients = clients.stream().filter(c -> c.getUsername().equals(username)).toList();

        if (foundClients.isEmpty()){
            throw new ClientNotFoundException(String.format("Account not found for username %s", username));
        }

        return foundClients.get(0);
    }

    @Override
    public Client getClientById(Long id) throws ClientNotFoundException {
        init();
        List<Client> foundClients = clients.stream().filter(c -> c.getId().equals(id)).toList();

        if (foundClients.isEmpty()){
            throw new ClientNotFoundException(String.format("Account not found for id %x", id));
        }
        return foundClients.get(0);
    }

    private void init(){
        if (clients==null){
            clients = new ArrayList<>();

            Client client = new Client();
            client.setUsername("username");
            client.setName("name");
            client.setSurname("surname");
            clients.add(client);
        }
    }
}
