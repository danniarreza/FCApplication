package nl.UTwente.FCApplication.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.UTwente.FCApplication.model.Product;
import nl.UTwente.FCApplication.repository.ProductRepository;

@Component
public class InitProductItems {
	
    @Autowired
	private ProductRepository productRepository;

    @PostConstruct
    public void init(){
		
		productRepository.save(new Product(1, "Chicken", "pcs"));
		productRepository.save(new Product(2, "Bread", "pcs"));
    }
    
}
