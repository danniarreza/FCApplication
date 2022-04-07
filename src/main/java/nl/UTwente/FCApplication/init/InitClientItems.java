package nl.UTwente.FCApplication.init;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.UTwente.FCApplication.model.Client;
import nl.UTwente.FCApplication.repository.ClientRepository;

@Component
public class InitClientItems {
	
    @Autowired
	private ClientRepository clientRepository;

    @PostConstruct
    public void init(){
		
		List<Client> clientList = clientRepository.findAll();

		if (clientList.size() == 0) {
			Client client = new Client(1, "McDonald's", "Drienerlolaan 10");
			clientRepository.save(client);
		}
    }
    
}
