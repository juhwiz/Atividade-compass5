package br.com.compass.apimercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.apimercado.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
