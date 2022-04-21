package nl.UTwente.FCApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.UTwente.FCApplication.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
