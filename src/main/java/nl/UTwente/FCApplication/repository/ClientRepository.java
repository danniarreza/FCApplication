package nl.UTwente.FCApplication.repository;

import org.springframework.stereotype.Service;

import nl.UTwente.FCApplication.model.Client;

@Service
public class ClientRepository {
    
    public Client getClient(){
        Client client = new Client("McDonald's", 23, "Drienerlolaan 10");
        return client;
    }
}
