package com.babelgroup.repositories.clients;

import com.babelgroup.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository implements IClientRepository{

    private List<Client> clients;

    @Override
    public Client getClientByUsername(String username) {
        init();
        return clients.stream().filter(c -> {
            System.out.println(c.getUsername() + " - " + username);
            System.out.println(c.getUsername().equals(username));
            return c.getUsername().equals(username);
        }).toList().get(0);
    }

    @Override
    public Client getClientById(Long id) {
        init();
        return clients.stream().filter(c -> c.getId().equals(id)).toList().get(0);
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
        Client client = new Client();
        client.setUsername("username");
        client.setName("name");
        client.setSurname("surname");
        clients.add(client);
    }
}
