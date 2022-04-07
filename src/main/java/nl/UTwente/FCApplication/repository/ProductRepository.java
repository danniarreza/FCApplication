package nl.UTwente.FCApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.UTwente.FCApplication.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
