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
import nl.UTwente.FCApplication.model.Goods;
import nl.UTwente.FCApplication.model.Product;
import nl.UTwente.FCApplication.repository.GoodsRepository;
import nl.UTwente.FCApplication.repository.ProductRepository;

@RestController
@Tag(name="Goods API", description="This is the Goods REST API")
public class GoodsController {
    
    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/goods/{id}")
    public Goods getGoods(@PathVariable int id){
        Goods goods = goodsRepository.findById(id).orElse(null);
        return goods;
    }

    @GetMapping("/goods")
    public List<Goods> getAllGoods(){
        List<Goods> goodsList = goodsRepository.findAll();
        return goodsList;
    }
    
    @PostMapping("/goods")
    public Goods createGoods(@RequestBody Goods goods){

        Product existingProduct = productRepository.findById(goods.getProduct().getProductId()).orElse(null);
        goods.setProduct(existingProduct);
        goodsRepository.save(goods);

        return goods;
    }

    @PutMapping("/goods/{id}")
    public Goods updateGoods(@PathVariable int id, @RequestBody Goods goods){

        Goods existingGoods = goodsRepository.findById(id).orElse(null);

        Product existingProduct = productRepository.findById(goods.getProduct().getProductId()).orElse(null);
        
        existingGoods.setProduct(existingProduct);
        existingGoods.setProduct(goods.getProduct());
        existingGoods.setAmount(goods.getAmount());
        goodsRepository.save(existingGoods);
        return existingGoods;
    }


    @DeleteMapping("/goods/{id}")
    public String deleteGoods(@PathVariable int id){

        goodsRepository.deleteById(id);
        return "Goods " + id + " deleted !";
    }
}
