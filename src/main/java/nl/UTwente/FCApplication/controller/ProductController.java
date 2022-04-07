package nl.UTwente.FCApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.UTwente.FCApplication.model.Product;
import nl.UTwente.FCApplication.repository.ProductRepository;

@RestController
@Tag(name="Product API", description="This is the Product REST API")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){

        productRepository.save(product);

        return product;
    }


    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    @GetMapping("/product")
    public List<Product> getAllProduct(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product){

        Product existingProduct = productRepository.findById(id).orElse(null);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductUnit(product.getProductUnit());
        productRepository.save(existingProduct);
        return existingProduct;
    }


    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id){

        productRepository.deleteById(id);
        return "Product " + id + " deleted !";
    }
    
}
