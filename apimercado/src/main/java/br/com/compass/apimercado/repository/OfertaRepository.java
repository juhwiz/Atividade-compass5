package br.com.compass.apimercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.apimercado.entity.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>{
    
}
