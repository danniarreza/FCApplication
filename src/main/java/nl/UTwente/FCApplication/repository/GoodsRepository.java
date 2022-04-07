package nl.UTwente.FCApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.UTwente.FCApplication.model.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    
}
